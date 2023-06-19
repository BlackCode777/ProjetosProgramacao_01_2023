package com.designAPIsRestFullSpringTddJunit3.libraryApi.service.impl;

import com.designAPIsRestFullSpringTddJunit3.libraryApi.api.exception.BusinessException;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.model.entity.Loan;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.repository.LoanRepository;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.service.LoanService;

public class LoanServiceImple implements LoanService {

    private final LoanRepository repository;

    public LoanServiceImple(LoanRepository repository) {
        this.repository = repository;
    }

    @Override
    public Loan save(Loan loan) {

        if( repository.existsByBookAndNotReturned( loan.getBook() ) ){
            throw new BusinessException( "Book already loaned" );
        }

        return repository.save(loan);
    }

}
