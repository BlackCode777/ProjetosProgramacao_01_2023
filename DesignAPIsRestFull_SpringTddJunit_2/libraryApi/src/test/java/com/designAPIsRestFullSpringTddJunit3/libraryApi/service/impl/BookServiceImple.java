package com.designAPIsRestFullSpringTddJunit3.libraryApi.service.impl;

import com.designAPIsRestFullSpringTddJunit3.libraryApi.api.exception.BusinessException;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.model.entity.Book;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.repositoy.BookRepository;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImple implements BookService {

    private BookRepository repository;

    public BookServiceImple( BookRepository repository ){
        this.repository = repository;
    }

    @Override
    public Book save(Book book) {

        if( repository.existByIsbn(book.getIsbn() ) ){
            throw new BusinessException("Isbn já cadastrado.");
        }
        return repository.save(book);
    }

}
