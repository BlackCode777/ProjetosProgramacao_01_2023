package com.designAPIsRestFullSpringTddJunit3.libraryApi.resource;

import com.designAPIsRestFullSpringTddJunit3.libraryApi.dto.BooKDTO;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.api.exception.BusinessException;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.model.entity.Book;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest // faz test unitario - testa comportamento API
@AutoConfigureMockMvc
public class BookControllerTest {
    //Definir a rota para o teste da API
    static String Book_Api = "/api/books";
    @Autowired
    MockMvc mvc;

    @MockBean//cria uma instancia mockada para injetar
    BookService service;

    // teste passo - 0
    // Chama classe BooKDTO com o .builder()
    // depois chama os campos da classe BooKDTO ( .title("As aventuras") .author("Arthur").isbn("001")  )
    // junto com a função .build()
    // Agora passa o bookDTO para linha 49 e linha  64 - 66, para pegar os valores dinâmicamente populando os campos
    // Simulando comportamento de salvar do metodo da classe Service linha 57
    // Simula Comportamento de salvar no banco um objeto Book populado - linha 60
    // teste passo - 1
    //Criar uma string para representar o json - linha 39
    //Transformar objetos em json - linha 40 - 43
    // teste passo - 2
    // .andExpect(MockMvcResultMatchers.status().isCreated()) - quando envio uma requisição eu espero que seja criado um registro no banco - linha 54,55
    // .andExpect( MockMvcResultMatchers.jsonPath("id") - espera o retorno de json do ' id ' não vazio  - linha 56
    // .isEmpty() - verifica se esta vazio - linha 56
    // .andExpect() -  espera o retorno de json do ' title '  - linha 57

    @Test
    @DisplayName("Deve criar um livro com sucesso!")
    public void  createBookTest() throws Exception{

        BooKDTO bookDTO = createNewBook();

        Book savedBook = Book.builder().id(13L).title("As aventuras").author("Arthur").isbn("001").build();

        BDDMockito.given(service.save( Mockito.any(Book.class))).willReturn(savedBook);

        String json = new ObjectMapper().writeValueAsString(bookDTO);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(Book_Api)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);
        mvc.perform( request )
                .andExpect(status().isCreated() )
                .andExpect( jsonPath("id").isNotEmpty())
                .andExpect( jsonPath( "title").value(bookDTO.getTitle()))
                .andExpect( jsonPath( "author").value(bookDTO.getAuthor()))
                .andExpect( jsonPath( "isbn").value(bookDTO.getIsbn()))
        ;   }

    @Test
    @DisplayName("Deve lançar um erro de validação quando não houver dados suficientes para criação de livro!")
    public void  createInvalidBookTest() throws Exception{

        String json = new ObjectMapper().writeValueAsString(new BooKDTO());//Para validar a existencia de dados precisamos do json dos objetos

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(Book_Api) //Pega as requisições para fazer a validação dos objetos - ate aqui objetos está vazio -nesse teste eu espero que de um erro pq o objeto esta vazio
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform( request ) // Aqui estou configurando uma mensagem de erro para cada campo do objeto Book ( title,author,isbn )
                .andExpect( status().isBadRequest() )
                .andExpect( jsonPath( "errors", hasSize( 3 ) ));
    }

    @Test
    @DisplayName("Deve lançar um erro ao tentar criar um livro com o isbn já existente")
    public void createBookWithDuplicatedIsbn() throws Exception {

        BooKDTO dto = createNewBook();
        String json = new ObjectMapper().writeValueAsString(dto);//Para validar a existencia de dados precisamos do json dos objetos
        String mensagemErro = "Isbn já cadastrado.";

        BDDMockito.given( service.save( Mockito.any( Book.class ) ) ) // enviando uma mensagem de erro ao salvar um isbn repetido - simulação
                .willThrow( new BusinessException(mensagemErro) ); //BusinessException() - significa erro da regra de negocio

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(Book_Api) //Pega as requisições para fazer a validação dos objetos - ate aqui objetos está vazio -nesse teste eu espero que de um erro pq o objeto esta vazio
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform( request ) // Aqui estou configurando uma mensagem de erro para cada campo do objeto Book ( title,author,isbn )
                .andExpect( status().isBadRequest() )
                .andExpect( jsonPath( "errors", hasSize( 1 ) ) )
                .andExpect(jsonPath( "errors[0]").value(mensagemErro) );
    }


    @Test
    @DisplayName("Deve obter informações de um livro.")
    public void getBookDetailsTest() throws Exception {
        //Cenário
        Long id = 1L;

        Book book = Book.builder()
                .id(id)
                .title(createNewBook().getTitle())
                .author(createNewBook().getAuthor())
                .isbn(createNewBook().getIsbn())
                .build();

        BDDMockito.given(service.getById(id)).willReturn(Optional.of(book));

        //execução (when)
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(Book_Api.concat("/" + id))
                .accept(MediaType.APPLICATION_JSON);
        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect( jsonPath("id").value(id))
                .andExpect( jsonPath( "title").value(createNewBook().getTitle()))
                .andExpect( jsonPath( "author").value(createNewBook().getAuthor()))
                .andExpect( jsonPath( "isbn").value(createNewBook().getIsbn()));

    }

    private static BooKDTO createNewBook() {
        return BooKDTO.builder().title("As aventuras").author("Arthur").isbn("001").build();
    }


}












