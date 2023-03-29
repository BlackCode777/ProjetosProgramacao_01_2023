package com.designAPIsRestFullSpringTddJunit3.libraryApi.repository;

import com.designAPIsRestFullSpringTddJunit3.libraryApi.model.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByIsbnTrue(String isbn);

    //boolean existsByIsbnTrue(String isbn);

    //boolean existByIsbnTrue(String isbn);

}
