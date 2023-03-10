package com.designAPIsRestFullSpringTddJunit3.libraryApi.resource;

import com.designAPIsRestFullSpringTddJunit3.libraryApi.dto.BooKDTO;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.model.entity.Book;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.designAPIsRestFullSpringTddJunit3.libraryApi.dto.BooKDTO.*;
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

    @Test
    @DisplayName("Deve criar um livro com sucesso!")
    public void  createBookTest() throws Exception{

        // teste passo - 0
        // Chama classe BooKDTO com o .builder()
        // depois chama os campos da classe BooKDTO ( .title("As aventuras") .author("Arthur").isbn("001")  )
        // junto com a fun????o .build()
        // Agora passa o bookDTO para linha 49 e linha  64 - 66, para pegar os valores din??micamente populando os campos
        BooKDTO bookDTO = BooKDTO.builder().title("As aventuras").author("Arthur").isbn("001").build();

        // Simulando comportamento de salvar do metodo da classe Service
        Book savedBook = Book.builder().id(13L).title("As aventuras").author("Arthur").isbn("001").build();

        // Simula Comportamento de salvar no banco um objeto Book populado *
        BDDMockito.given(service.save( Mockito.any(Book.class))).willReturn(savedBook);

        // teste passo - 1
        //Criar uma string para representar o json - linha 39
        //Transformar objetos em json - linha 40 - 43

        String json = new ObjectMapper().writeValueAsString(bookDTO);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(Book_Api)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        // teste passo - 2
        // .andExpect(MockMvcResultMatchers.status().isCreated()) - quando envio uma requisi????o eu espero que seja criado um registro no banco - linha 54,55
        // .andExpect( MockMvcResultMatchers.jsonPath("id") - espera o retorno de json do ' id ' n??o vazio  - linha 56
        // .isEmpty() - verifica se esta vazio - linha 56
        // .andExpect() -  espera o retorno de json do ' title '  - linha 57

        mvc.perform( request )
                .andExpect(status().isCreated() )
                .andExpect( jsonPath("id").isNotEmpty())
                .andExpect( jsonPath( "title").value(bookDTO.getTitle()))
                .andExpect( jsonPath( "author").value(bookDTO.getAuthor()))
                .andExpect( jsonPath( "isbn").value(bookDTO.getIsbn()))
        ;   }

    @Test
    @DisplayName("Deve lan??ar um erro de valida????o quando n??o houver dados suficientes para cria????o de livro!")
    public void  createInvalidBookTest(){

    }


}
