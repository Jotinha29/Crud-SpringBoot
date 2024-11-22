package com.example.demo.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tbl_livros")
@Data
public class LivroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private Long autorId;

    @Column(nullable = false)
    private Long categoriaId;
}
