package com.designAPIsRestFullSpringTddJunit3.libraryApi.model.repository;

import com.designAPIsRestFullSpringTddJunit3.libraryApi.repositoy.BookRepository;
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

    @Autowired
    TestEntityManager entityManager;

    BookRepository repository;

    @Test
    @DisplayName("Deve retornar verdadeiro quando existir um livro na base de dados com i isbn informado")
    public void returnTrueWhenIsbnExists(){
        //Cenário
        String isbn = "123";
        //Execução
        boolean exists = repository.existByIsbn(isbn);
        //Verificação
        assertThat(exists).isTrue();
    }
}
