package com.designAPIsRestFullSpringTddJunit3.libraryApi.service;

import com.designAPIsRestFullSpringTddJunit3.libraryApi.api.exception.BusinessException;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.model.entity.Book;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.model.entity.Loan;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.repository.LoanRepository;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.service.impl.LoanServiceImple;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class LoanServiceTest {

    LoanService service;

    @MockBean
    LoanRepository repository;

    @BeforeEach
    public void setUp(){
        this.service = new LoanServiceImple(repository);
    }

    @Test
    @DisplayName(" Deve salvar um emprestimo ")
    public void saveLoanTest(){
        Object customer = "Fulano";
        Book book = Book.builder().id(1L).build();

        Loan savingLoan = Loan
                .builder()
                .book(book)
                .customer((String) customer)
                .loanDate(String.valueOf(LocalDate.now()))
                .build();
        Loan saveLoan= Loan
                .builder()
                .id(1L)
                .customer((String) customer)
                .loanDate(String.valueOf(LocalDate.now()))
                .build();

        when( repository.existsByBookAndNotReturned( book ) ).thenReturn( false );
        when( repository.save( savingLoan ) ).thenReturn( saveLoan );

        Loan loan = service.save( savingLoan );

        Assertions.assertThat( loan.getId() ).isEqualTo( saveLoan.getId() );
        Assertions.assertThat( loan.getBook() ).isEqualTo( saveLoan.getBook() );
        Assertions.assertThat( loan.getCustomer() ).isEqualTo( saveLoan.getCustomer() );
        Assertions.assertThat( loan.getLoanDate() ).isEqualTo( saveLoan.getLoanDate() );
    }

    @Test
    @DisplayName(" Deve lançar um erro de negócio ao salvar um emprestimo com um livro já emprestado. ")
    public void loanedBookSaveTest(){
        Object customer = "Fulano";
        Book book = Book.builder().id(1L).build();

        Loan savingLoan =
                Loan.builder()
                        .book(book)
                        .customer((String) customer)
                        .loanDate(String.valueOf(LocalDate.now()))
                        .build();

        //Loan loan = service.save( savingLoan );
        when( repository.existsByBookAndNotReturned( book ) ).thenReturn( true );

        Throwable exception = catchThrowable( () -> service.save( savingLoan ) );

        assertThat( exception )
                .isInstanceOf( BusinessException.class )
                .hasMessage( "Book already loaned" );

        verify( repository, never() ).save( savingLoan );

    }


}
