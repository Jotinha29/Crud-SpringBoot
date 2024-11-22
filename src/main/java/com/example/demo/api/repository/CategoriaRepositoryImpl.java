package com.example.demo.api.repository;

import com.example.demo.api.dto.AutorDTO;
import com.example.demo.api.dto.CategoriaDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.*;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoriaRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public List<CategoriaDTO> getListarCategorias() {
        String sql = getAllCategoriasSQL();

        Query query = entityManager.createNativeQuery(sql, "ListarCategoriaDTOMapping");

        return (List<CategoriaDTO>) query.getResultList();
    }

    public String getAllCategoriasSQL() {
        return """
                SELECT
                    c.id AS id,
                    c.nome AS nome
                FROM
                    tbl_categorias c
                """;
    }

    public List<CategoriaDTO> getCategoriaByID(Long id) {

        String sql = getCategoriaByIDSQL();

        Query query = entityManager.createNativeQuery(sql, "ListarCategoriaDTOMapping");
        query.setParameter("id", id);
        return query.getResultList();
    }

    public String getCategoriaByIDSQL() {
        return """
                SELECT
                    c.id AS id,
                    c.nome AS nome
                FROM
                    tbl_categorias c
                WHERE id = :id
                """;
    }
}

