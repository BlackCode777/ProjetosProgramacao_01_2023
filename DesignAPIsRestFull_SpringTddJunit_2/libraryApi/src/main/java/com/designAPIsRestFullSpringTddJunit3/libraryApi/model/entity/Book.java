package com.designAPIsRestFullSpringTddJunit3.libraryApi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    public Long id;
    public String title;
    public String author;
    public String isbn;

}
