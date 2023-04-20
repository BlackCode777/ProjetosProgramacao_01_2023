package com.designAPIsRestFullSpringTddJunit3.libraryApi.repository;

import com.designAPIsRestFullSpringTddJunit3.libraryApi.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByIsbn(String isbn);

    Optional<Book> findByIsbn(String isbn);
    //org.mockito.stubbing.OngoingStubbing<Optional<Book>> findByIsbn(String isbn);
}
