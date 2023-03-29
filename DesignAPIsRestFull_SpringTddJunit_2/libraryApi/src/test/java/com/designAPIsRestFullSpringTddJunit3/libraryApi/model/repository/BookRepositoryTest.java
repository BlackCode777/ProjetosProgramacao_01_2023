package com.designAPIsRestFullSpringTddJunit3.libraryApi.model.repository;

import com.designAPIsRestFullSpringTddJunit3.libraryApi.model.entity.Book;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

// Testando a integração com o banco de dados
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest // Cria uma instancia do banco de dados em memória
public class BookRepositoryTest {

    // Teste de integração com banco H2 falhou - objeto de pesquisa
    // como fazer teste de integralção entre banco H2 / mokito /

//    @Autowired // Fundamental para poder usar o TestEntityManager
//    TestEntityManager entityManager; // esta classe TestEntityManager simula o banco de dados em memoria
//    @Autowired
//    BookRepository repository;

//    @Test
//    @DisplayName("Deve retornar True quando existir um livro na base de dados com i isbn informado")
//    public void returnTrueWhenIsbnExists(){
//        //Cenário
//        String isbn = "123";
//        Book book = Book.builder().title("As aventuras").author("Fulano").isbn(isbn).build();
//        entityManager.persist(book); // Usado para persistir os dados no banco em memória
//        //Execução
//        boolean exists = Boolean.parseBoolean(String.valueOf(repository.existsByIsbnTrue(isbn)));
//        //Verification
//        assertThat(exists).isTrue();
//    }

//    @Test
//    @DisplayName("Deve retornar False quando não existir um livro na base de dados com i isbn informado")
//    public void returnFalseWhenIsbnExists(){
//        //Cenário
//        String isbn = "123";
//        Book book = Book.builder().title("As aventuras").author("Fulano").isbn(isbn).build();
//        entityManager.persist(book); // Usado para persistir os dados no banco em memória
//        //Execução
//        boolean exists = Boolean.parseBoolean(String.valueOf(repository.existsByIsbnTrue(isbn)));
//        //Verification
//        assertThat(exists).isFalse();
//    }



}
