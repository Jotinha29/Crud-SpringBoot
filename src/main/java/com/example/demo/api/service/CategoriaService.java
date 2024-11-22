package com.example.demo.api.service;

import com.example.demo.api.dto.CategoriaDTO;
import com.example.demo.api.model.CategoriaEntity;
import com.example.demo.api.repository.CategoriaRepository;
import com.example.demo.api.repository.CategoriaRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepositoryImpl categoriaRepositoryImpl;

    private final CategoriaRepository categoriaRepository;

    public List<CategoriaDTO> listarCategoria() {
        return categoriaRepositoryImpl.getListarCategorias();
    }

    public CategoriaDTO listarCategoriaById(Long id) {
        List<CategoriaDTO> categoria = categoriaRepositoryImpl.getCategoriaByID(id);
        if (categoria.isEmpty()) {
            return null;
        }
        return categoria.get(0);
    }

    public CategoriaDTO cadastrarCategoria(CategoriaDTO request) {
        CategoriaEntity categoriaEntity = converterParaEntity(request);
        CategoriaEntity categoriaSalvo = categoriaRepository.save(categoriaEntity);
        return converterParaDTO(categoriaSalvo);
    }

    public CategoriaDTO atualizarCategoria(Long id, CategoriaDTO request) {
        CategoriaEntity categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria com ID " + id + " não encontrado."));

        categoriaExistente.setNome(request.getNome());

        CategoriaEntity categoriaAtualizada = categoriaRepository.save(categoriaExistente);

        return converterParaDTO(categoriaAtualizada);
    }

    public void deletarCategoria(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new IllegalArgumentException("Categoria com ID " + id + " não encontrado.");
        }
        categoriaRepository.deleteById(id);
    }

    private CategoriaEntity converterParaEntity(CategoriaDTO dto) {
        CategoriaEntity categoria = new CategoriaEntity();
        categoria.setNome(dto.getNome());
        return categoria;
    }

    private CategoriaDTO converterParaDTO(CategoriaEntity entity) {
        return new CategoriaDTO(entity.getId(), entity.getNome());
    }
}

