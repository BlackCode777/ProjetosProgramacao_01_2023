package com.designAPIsRestFullSpringTddJunit3.libraryApi.resource;

import com.designAPIsRestFullSpringTddJunit3.libraryApi.controller.BookController;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.controller.LoanController;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.dto.LoanDto;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.model.entity.Book;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.model.entity.Loan;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.service.BookService;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.service.LoanService;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.service.impl.BookServiceImple;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest( controllers = LoanController.class) // faz test unitario - testa comportamento API
@AutoConfigureMockMvc
public class LoanControllerTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    BookService bookService;
    @MockBean
    private LoanService loanService;
    @Test
    @DisplayName(" Deve realizar um emprestimo ")
    public void createLoanTest() throws Exception {
        final String LOAN_API = "/api/loans";
        // Cria o objeto json que para o MockHttpServletRequestBuilder que forma a request para envio
        LoanDto dto = LoanDto.builder().isbn("123").customer("Fulano").build();
        String json = new ObjectMapper().writeValueAsString(dto);
        // Quando o isbn for passado na requisição, terá de ser validado e será buscado na base de dados
        Book book = Book.builder().id( 1L ).isbn( "123" ).build();
        BDDMockito.given( bookService.getBookByIsbn( "123" ) ).willReturn( Optional.of( book ) ); // Aqui o BDDMockito simula que o livro emprestado esteja na base de dados - vai buscar na base
        // depois que o livro for verificado na base ele faz o emprestimo do livro para o customer e salva na base com o id/customer/book/dataDoEmprestimo
        Loan loan = Loan.builder().id( 1L ).customer( "Fulano" ).book( book ).loanDate( LocalDate.now() ).build();
        BDDMockito.given( loanService.save(Mockito.any( Loan.class ) ) ).willReturn( loan ) ;// Cria o Objeto
        // Quando a requisição for passada
        MockHttpServletRequestBuilder request =  MockMvcRequestBuilders.post( LOAN_API ) // Aqui ele persiste o livro emprestado na base
                .accept( MediaType.APPLICATION_JSON )
                .contentType( MediaType.APPLICATION_JSON )
                .content( json );
        // Eu espero que seja criada na base um registro - ResultActions resultActions =
        mvc.perform( request )
                .andExpect( status().isCreated() )
                .andExpect( content().string( "1" ) );
    }

}












