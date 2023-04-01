package com.designAPIsRestFullSpringTddJunit3.libraryApi.repository;

import com.designAPIsRestFullSpringTddJunit3.libraryApi.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByIsbn(String isbn);

}
