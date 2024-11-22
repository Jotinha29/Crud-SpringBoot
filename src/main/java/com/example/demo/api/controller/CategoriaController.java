package com.example.demo.api.controller;

import com.example.demo.api.dto.AutorDTO;
import com.example.demo.api.dto.CategoriaDTO;
import com.example.demo.api.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/categoria/")
public class  CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping(value = "/listar")
    public ResponseEntity<List<CategoriaDTO>> listarCategoria() {
        List<CategoriaDTO> listaCategoria = categoriaService.listarCategoria();
        return new ResponseEntity<>(listaCategoria, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> listarCategoriaPorId(@PathVariable Long id) {
        CategoriaDTO autor = categoriaService.listarCategoriaById(id);
        if (autor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(autor);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<CategoriaDTO> cadastrarCategoria(@RequestBody CategoriaDTO request) {
        CategoriaDTO categoria = categoriaService.cadastrarCategoria(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> atualizarCategoria(@PathVariable Long id, @RequestBody CategoriaDTO request) {
        try {
            CategoriaDTO categoriaAtualizada = categoriaService.atualizarCategoria(id, request);
            return ResponseEntity.ok(categoriaAtualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {
        try {
            categoriaService.deletarCategoria(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
