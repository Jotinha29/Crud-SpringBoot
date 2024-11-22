package com.example.demo.api.controller;

import com.example.demo.api.dto.LivroDTO;
import com.example.demo.api.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/livros/")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping(value = "/listar")
    public ResponseEntity<List<LivroDTO>> listarLivros() {
        List<LivroDTO> listaLivros = livroService.getListarLivros();
        return new ResponseEntity<>(listaLivros, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> buscarLivroPorId(@PathVariable Long id) {
        LivroDTO livro = livroService.listarLivroById(id);
        if (livro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(livro);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<LivroDTO> cadastrarLivro(@RequestBody LivroDTO request) {
        LivroDTO livro = livroService.cadastrarLivro(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(livro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDTO> atualizarLivro(@PathVariable Long id, @RequestBody LivroDTO request) {
        try {
            LivroDTO livroAtualizado = livroService.atualizarLivro(id, request);
            return ResponseEntity.ok(livroAtualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarLivro(@PathVariable Long id) {
        try {
            String mensagem = livroService.deletarLivro(id);
            return ResponseEntity.ok(mensagem);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
