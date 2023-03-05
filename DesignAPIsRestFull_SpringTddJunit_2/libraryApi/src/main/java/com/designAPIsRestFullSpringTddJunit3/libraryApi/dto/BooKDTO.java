package com.designAPIsRestFullSpringTddJunit3.libraryApi.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BooKDTO {

    public Long id;
    public String title;
    public String author;
    public String isbn;


}
