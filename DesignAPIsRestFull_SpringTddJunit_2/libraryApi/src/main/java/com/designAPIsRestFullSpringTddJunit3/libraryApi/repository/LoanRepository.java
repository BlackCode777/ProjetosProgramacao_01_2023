package com.designAPIsRestFullSpringTddJunit3.libraryApi.repository;

import com.designAPIsRestFullSpringTddJunit3.libraryApi.model.entity.Book;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.model.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    // Se a contagem de emprestimos for maior do que 0 retorne true
    @Query( value = " select case when ( count( l.id ) > 0 ) then true else false end from Loan l " +
            "where l.book = :book and ( l.returned is null or l.returned is false )  ")
    boolean existsByBookAndNotReturned( @Param( "book" ) Book book );
}
