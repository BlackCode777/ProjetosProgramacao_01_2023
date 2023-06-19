package com.designAPIsRestFullSpringTddJunit3.libraryApi.dto;

import com.designAPIsRestFullSpringTddJunit3.libraryApi.model.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class BooKDTO { //extends Book

    public Long id;

    //@NotEmpty
    public String title;

    //@NotEmpty
    public String author;

    //@NotEmpty
    public String isbn;
    

}
