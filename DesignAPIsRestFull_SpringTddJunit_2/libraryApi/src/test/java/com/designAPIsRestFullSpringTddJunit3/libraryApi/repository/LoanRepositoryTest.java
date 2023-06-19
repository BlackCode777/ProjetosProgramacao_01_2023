package com.designAPIsRestFullSpringTddJunit3.libraryApi.repository;

import com.designAPIsRestFullSpringTddJunit3.libraryApi.model.entity.Book;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.model.entity.Loan;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;


import static com.designAPIsRestFullSpringTddJunit3.libraryApi.model.repository.BookRepositoryTest.createNewBook;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class LoanRepositoryTest {

    @Autowired
    private LoanRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName( "deve verificar se existe um emprestimo não devolvido para o livro" )
    public void existsByBookAndNotReturned(){
        //cenário
        Book book = createNewBook("123"); //("123")
        entityManager.persist( book );

        Loan loan = Loan.builder().book(book).customer("Fulano").loanDate(String.valueOf(LocalDate.now())).build();
        entityManager.persist( loan );

        boolean exists = repository.existsByBookAndNotReturned( book );

        //execução
        repository.existsByBookAndNotReturned( book );

        assertThat( exists ).isTrue();

    }


}
