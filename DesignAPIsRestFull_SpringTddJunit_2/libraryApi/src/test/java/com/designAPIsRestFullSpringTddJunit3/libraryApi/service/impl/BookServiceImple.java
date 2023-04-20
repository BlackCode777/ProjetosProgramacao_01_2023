package com.designAPIsRestFullSpringTddJunit3.libraryApi.service.impl;

import com.designAPIsRestFullSpringTddJunit3.libraryApi.api.exception.BusinessException;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.model.entity.Book;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.repository.BookRepository;
import com.designAPIsRestFullSpringTddJunit3.libraryApi.service.BookService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImple implements BookService {

    private BookRepository repository;

    public BookServiceImple( BookRepository repository ){
        this.repository = repository;
    }

    @Override
    public Book save(Book book) {

        if( repository.existsByIsbn(book.getIsbn() ) ){
            throw new BusinessException("Isbn já cadastrado.");
        }
        return repository.save(book);
    }

    @Override
    public Optional<Book> getById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public void delete(Book book) {

        if( book == null || book.getId() == null ){
            throw new IllegalArgumentException(" Book id cant be null. ");
        }

        this.repository.delete(book);

    }

    @Override
    public Book update(Book book) {
        if( book == null || book.getId() == null ){
            throw new IllegalArgumentException(" Book id cant be null. ");
        }
        return this.repository.save(book); // OBS - importante o metodo save() tanto salva quanto atualiza no JPA
    }

    @Override
    public Page<Book> find(Book filter, Pageable pageRequest) {
        Example<Book> example = Example.of( filter,
                ExampleMatcher
                        .matching()
                        .withIgnoreCase()
                        .withIgnoreNullValues()
                        .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING )
                );
        return repository.findAll( example, pageRequest );
    }

    @Override
    public Optional<Book> getBookByIsbn(String isbn) {
        return repository.findByIsbn(isbn);
    }

}
