package com.example.demo.api.service;

import com.example.demo.api.dto.CategoriaDTO;
import com.example.demo.api.dto.LivroDTO;
import com.example.demo.api.model.CategoriaEntity;
import com.example.demo.api.model.LivroEntity;
import com.example.demo.api.repository.LivroRepository;
import com.example.demo.api.repository.LivroRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepositoryImpl livroRepositoryImpl;

    private final LivroRepository livroRepository;

    public List<LivroDTO> getListarLivros() {
        return livroRepositoryImpl.getListarLivros();
    }

    public LivroDTO listarLivroById(Long id) {
        List<LivroDTO> livro = livroRepositoryImpl.getLivroByID(id);
        if (livro.isEmpty()) {
            return null;
        }
        return livro.get(0);
    }

    public LivroDTO cadastrarLivro(LivroDTO request) {
        LivroEntity livroEntity = converterParaEntity(request);
        LivroEntity livroSalvo = livroRepository.save(livroEntity);
        return listarLivroById(livroSalvo.getId());
    }

    public LivroDTO atualizarLivro(Long id, LivroDTO request) {
        LivroEntity livroExistente = livroRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Livro com ID " + id + " não encontrado."));

        // Atualiza os campos do livro
        livroExistente.setTitulo(request.getTitulo());
        livroExistente.setIsbn(request.getIsbn());
        livroExistente.setAutorId(request.getAutorId());
        livroExistente.setCategoriaId(request.getCategoriaId());

        LivroEntity livroAtualizado = livroRepository.save(livroExistente);

        return listarLivroById(livroAtualizado.getId());
    }


    public String deletarLivro(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new IllegalArgumentException("Livro com ID " + id + " não encontrado.");
        }
        livroRepository.deleteById(id);
        return "Livro com ID " + id + " foi excluído com sucesso.";
    }

    private LivroEntity converterParaEntity(LivroDTO dto) {
        LivroEntity livro = new LivroEntity();
        livro.setTitulo(dto.getTitulo());
        livro.setIsbn(dto.getIsbn());
        livro.setAutorId(dto.getAutorId());
        livro.setCategoriaId(dto.getCategoriaId());
        return livro;
    }

    private LivroDTO converterParaDTO(LivroEntity entity) {
        return new LivroDTO(
                entity.getId(),
                entity.getTitulo(),
                entity.getIsbn(),
                entity.getAutorId(),
                entity.getCategoriaId(),
                null,
                null
        );
    }
}
