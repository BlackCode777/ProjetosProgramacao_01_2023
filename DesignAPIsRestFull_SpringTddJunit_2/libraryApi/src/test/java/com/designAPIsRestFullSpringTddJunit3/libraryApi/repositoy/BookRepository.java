package com.designAPIsRestFullSpringTddJunit3.libraryApi.repositoy;

import com.designAPIsRestFullSpringTddJunit3.libraryApi.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existByIsbn(String isbn);
}
