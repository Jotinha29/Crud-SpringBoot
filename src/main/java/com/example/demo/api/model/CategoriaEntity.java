package com.example.demo.api.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "tbl_categorias")
@Data
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;
}