package com.designAPIsRestFullSpringTddJunit3.libraryApi.service;

import com.designAPIsRestFullSpringTddJunit3.libraryApi.api.exception.BusinessException;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.dto.BooKDTO;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.model.entity.Book;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.repositoy.BookRepository;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.service.impl.BookServiceImple;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class BookServiceTest { // Teste usado para fazer somente testr unitário // Só é feito test unitário na classe de serviços

    BookService service;

    @MockBean
    BookRepository repository;

    @BeforeEach
    public void setUp(){
        this.service = new BookServiceImple( repository );
    }

    private static Book createValidBook() {
        return Book.builder().isbn("123").author("Fulano").title("As aventuras").build();
    }

    @Test
    @DisplayName("Deve lançar um erro de negócio ao tentar salvar um livro com isbn duplicado !")
    public void shouldNotSaveABokkWithDuplicatedIsbn() throws Exception {
        //cenario
        Book book = createValidBook();
        Mockito.when( repository.existByIsbn( Mockito.anyString() ) ).thenReturn( true );
        //execução
        Throwable exception = catchThrowable(() -> service.save(book) );
        //Verificação
        assertThat( exception ).isInstanceOf( BusinessException.class ).hasMessage( "Isbn já cadastrado." );
        //Verificação - teste da chamada do metodo save() do repository
        Mockito.verify( repository, Mockito.never() ).save(book);
    }

    @Test
    @DisplayName("Deve salvar um livro")
    public  void saveBookTest(){
        Book book = createValidBook(); //Cenário de teste >>> ERRO AQUI >>> Book book = Book.builder().isbn("123").author("Fulano").title("As aventuras").build(); = solução criar o metodo
        Mockito.when( repository.existByIsbn( Mockito.anyString() ) ).thenReturn( false );
        Mockito.when( repository.save( book ) ).thenReturn( // O teste aqui passa, pq criamos um livro mockado fake
                Book.builder()
                    .id(1L)
                    .isbn("123")
                    .author("Fulano")
                    .title("As aventuras")
                    .build()
        );
        //Execução do teste
        Book savedBook = service.save(book);
        //Verificação se o método esta salvando // enquanto o metodo estiver null - BookServiceImple()
        assertThat(savedBook.getId()).isNotNull(); // gera esse erro >java.lang.NullPointerException - at com.designAPIsRestFullSpringTddJunit3.libraryApi.service.BookServiceTest.saveBookTest(BookServiceTest.java:34)
        assertThat(savedBook.getIsbn()).isEqualTo("123");
        assertThat(savedBook.getTitle()).isEqualTo("As aventuras");
        assertThat(savedBook.getAuthor()).isEqualTo("Fulano");
    }




}
