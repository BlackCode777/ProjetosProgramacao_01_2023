package com.designAPIsRestFullSpringTddJunit3.libraryApi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Book")
public class Book {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(name = "title")
    public String title;
    @Column(name = "author")
    public String author;
    @Column(name = "isbn")
    public String isbn;

}
