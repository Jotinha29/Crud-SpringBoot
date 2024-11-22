package com.example.demo.api.service;

import com.example.demo.api.dto.AutorDTO;
import com.example.demo.api.model.AutorEntity;
import com.example.demo.api.repository.AutorRepository;
import com.example.demo.api.repository.AutorRepositoryImpl;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepositoryImpl autorRepositoryImpl;

    private final AutorRepository autorRepository;

    public List<AutorDTO> listarAutores() {
        return autorRepositoryImpl.getListarAutores();
    }

    public AutorDTO listarAutorById(Long id) {
        List<AutorDTO> autores = autorRepositoryImpl.getAutorById(id);
        if (autores.isEmpty()) {
            return null;
        }
        return autores.get(0);
    }


    public AutorDTO cadastrarAutor(AutorDTO request) {
        AutorEntity autorEntity = converterParaEntity(request);
        AutorEntity autorSalvo = autorRepository.save(autorEntity);
        return converterParaDTO(autorSalvo);
    }

    public AutorDTO atualizarAutor(Long id, AutorDTO request) {
        AutorEntity autorExistente = autorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Autor com ID " + id + " não encontrado."));

        autorExistente.setNome(request.getNome());
        autorExistente.setNacionalidade(request.getNacionalidade());

        AutorEntity autorAtualizado = autorRepository.save(autorExistente);

        return converterParaDTO(autorAtualizado);
    }


    public void deletarAutor(Long id) {
        if (!autorRepository.existsById(id)) {
            throw new IllegalArgumentException("Autor com ID " + id + " não encontrado.");
        }
        autorRepository.deleteById(id);
    }

    private AutorEntity converterParaEntity(AutorDTO dto) {
        AutorEntity autor = new AutorEntity();
        autor.setNome(dto.getNome());
        autor.setNacionalidade(dto.getNacionalidade());
        return autor;
    }

    private AutorDTO converterParaDTO(AutorEntity entity) {
        return new AutorDTO(entity.getId(), entity.getNome(), entity.getNacionalidade());
    }
}
