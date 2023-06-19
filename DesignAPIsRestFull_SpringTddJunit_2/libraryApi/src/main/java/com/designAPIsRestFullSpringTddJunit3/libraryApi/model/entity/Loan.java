package com.designAPIsRestFullSpringTddJunit3.libraryApi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public Long id;

    @Column
    public String customer;

    @Column
    public String loanDate;

    @JoinColumn( name = "idBook")
    @ManyToOne
    public Book book;

    @Column
    public Boolean returned;

}
