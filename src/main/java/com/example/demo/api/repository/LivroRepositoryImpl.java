package com.example.demo.api.repository;

import com.example.demo.api.dto.LivroDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class LivroRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Listar todos os livros com nomes de autor e categoria.
     */
    public List<LivroDTO> getListarLivros() {
        String sql = getSqlLista();

        Query query = entityManager.createNativeQuery(sql, "ListarLivroDTOMapping");

        return (List<LivroDTO>) query.getResultList();
    }

    private String getSqlLista() {
        return """
                SELECT
                    l.id AS id,
                    l.titulo AS titulo,
                    l.isbn AS isbn,
                    l.autor_id AS autorId,
                    l.categoria_id AS categoriaId,
                    a.nome AS nomeAutor,
                    c.nome AS nomeCategoria
                FROM
                    tbl_livros l
                JOIN tbl_autores a ON l.autor_id = a.id
                JOIN tbl_categorias c ON l.categoria_id = c.id
                """;
    }

    /**
     * Buscar livro por ID com nomes de autor e categoria.
     */
    public List<LivroDTO> getLivroByID(Long id) {
        String sql = getLivroByIDSQL();

        Query query = entityManager.createNativeQuery(sql, "ListarLivroDTOMapping");
        query.setParameter("id", id);

        return (List<LivroDTO>) query.getResultList();
    }

    private String getLivroByIDSQL() {
        return """
                SELECT
                    l.id AS id,
                    l.titulo AS titulo,
                    l.isbn AS isbn,
                    l.autor_id AS autorId,
                    l.categoria_id AS categoriaId,
                    a.nome AS nomeAutor,
                    c.nome AS nomeCategoria
                FROM
                    tbl_livros l
                JOIN tbl_autores a ON l.autor_id = a.id
                JOIN tbl_categorias c ON l.categoria_id = c.id
                WHERE l.id = :id
                """;
    }
}
