package com.designAPIsRestFullSpringTddJunit3.libraryApi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanDto {

    // @NotEmpty

    private String isbn;
    private String customer;

}
