package com.example.demo.api.repository;

import com.example.demo.api.dto.AutorDTO;
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
public class AutorRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public List<AutorDTO> getListarAutores() {
        String sql = getAllAutoresSQL();

        Query query = entityManager.createNativeQuery(sql, "ListarAutorDTOMapping");

        return (List<AutorDTO>) query.getResultList();
    }

    public String getAllAutoresSQL() {
        return """
                SELECT
                    a.id AS id,
                    a.nome AS nome,
                    a.nacionalidade AS nacionalidade
                FROM
                    tbl_autores a
                    
                """;
    }

    public List<AutorDTO> getAutorById(Long id) {

        String sql = getAutorByIdSQL();

        Query query = entityManager.createNativeQuery(sql, "ListarAutorDTOMapping");
        query.setParameter("id", id);
        return query.getResultList();
    }

    public String getAutorByIdSQL() {
        return """
                SELECT
                    a.id AS id,
                    a.nome AS nome,
                    a.nacionalidade AS nacionalidade
                FROM
                    tbl_autores a
                WHERE id = :id
                """;
    }
}

