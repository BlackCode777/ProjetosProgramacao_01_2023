package com.designAPIsRestFullSpringTddJunit3.libraryApi.service.impl;

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
        return repository.save(book);
    }

}
