package com.designAPIsRestFullSpringTddJunit3.libraryApi.service;

import com.designAPIsRestFullSpringTddJunit3.libraryApi.api.exception.BusinessException;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.model.entity.Book;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.repository.BookRepository;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.service.impl.BookServiceImple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


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
    @DisplayName("Deve salvar um livro")
    public  void saveBookTest(){
        Book book = createValidBook(); //Cenário de teste >>> ERRO AQUI >>> Book book = Book.builder().isbn("123").author("Fulano").title("As aventuras").build(); = solução criar o metodo
        Mockito.when( repository.existsByIsbn( Mockito.anyString() ) ).thenReturn( false );
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

    @Test
    @DisplayName("Deve lançar um erro de negócio ao tentar salvar um livro com isbn duplicado !")
    public void shouldNotSaveABokkWithDuplicatedIsbn() throws Exception {
        //cenario
        Book book = createValidBook();

        Mockito.when( repository.existsByIsbn( Mockito.anyString() ) ).thenReturn( true );

        //execução
        final  Throwable exception = catchThrowable( () -> service.save(book) ); // REF -> https://www.tabnine.com/code/java/methods/org.assertj.core.api.Assertions/catchThrowable

        //Verificação
        assertThat( exception ).isInstanceOf( BusinessException.class ).hasMessage( "Isbn já cadastrado." );

        //Verificação - teste da chamada do metodo save() do repository
        Mockito.verify( repository, Mockito.never() ).save(book);
    }

    @Test
    @DisplayName("Deve obter um livro por id")
    public void getBookForIDTest() throws  Exception {
        // Cenário
        Long id = 1L;
        Book book = createValidBook();
        book.setId(id);
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(book));

        //Execução
        Optional<Book> foundBook = service.getById(id);

        //Verificação
        assertThat( foundBook.isPresent() ).isTrue();
        assertThat( foundBook.get().getId() ).isEqualTo( id );
        assertThat( foundBook.get().getAuthor() ).isEqualTo( book.getAuthor() );
        assertThat( foundBook.get().getIsbn() ).isEqualTo( book.getIsbn() );
        assertThat( foundBook.get().getTitle() ).isEqualTo( book.getTitle() );
    }

    @Test
    @DisplayName("Deve retornar vazio ao tentar obter um livro por id quando ele não existir")
    public void bookNotFoundById_RetornarFalseTest() throws  Exception {
        // Cenário
        Long id = 1L;

        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        //Execução
        Optional<Book> book = service.getById(id);

        //Verificação
        assertThat( book.isPresent() ).isFalse();
    }


}
