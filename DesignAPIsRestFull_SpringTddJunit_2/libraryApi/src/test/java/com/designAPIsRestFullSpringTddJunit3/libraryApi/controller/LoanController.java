package com.designAPIsRestFullSpringTddJunit3.libraryApi.controller;


import com.designAPIsRestFullSpringTddJunit3.libraryApi.dto.LoanDto;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.model.entity.Book;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.model.entity.Loan;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.service.BookService;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private LoanService service;
    private BookService bookService;

    @PostMapping
    @ResponseStatus( HttpStatus.CREATED )
    public Long create( @RequestBody LoanDto dto ){
        // Criando um Book
        Book book = bookService
                        .getBookByIsbn(dto.getIsbn() )
                        .orElseThrow( () ->
                            new ResponseStatusException( HttpStatus.BAD_REQUEST, "Book not found for passed isbn." ) );
        // Criando um Loan
        Loan entity = Loan.builder()
                        .book( book )
                        .customer( dto.getCustomer() )
                        .loanDate( LocalDate.now() )
                        .build();
        entity = service.save( entity );
        return entity.getId();
    }

}
