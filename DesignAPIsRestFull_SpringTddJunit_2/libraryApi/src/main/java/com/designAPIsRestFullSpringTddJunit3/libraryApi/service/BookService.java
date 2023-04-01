package com.designAPIsRestFullSpringTddJunit3.libraryApi.service;

import com.designAPIsRestFullSpringTddJunit3.libraryApi.model.entity.Book;
import org.springframework.stereotype.Service;

@Service
public interface BookService {

    Book save(Book any);

}
