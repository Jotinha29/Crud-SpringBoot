package com.example.demo.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tbl_autores")
@Data
public class AutorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String nacionalidade;
}