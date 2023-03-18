package com.designAPIsRestFullSpringTddJunit3.libraryApi.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BooKDTO {

    public Long id;

    @NotEmpty
    public String title;

    @NotEmpty
    public String author;

    @NotEmpty
    public String isbn;

}
