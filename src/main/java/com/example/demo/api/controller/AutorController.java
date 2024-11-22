package com.example.demo.api.controller;

import com.example.demo.api.dto.AutorDTO;
import com.example.demo.api.model.AutorEntity;
import com.example.demo.api.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/autor/")
public class  AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping(value = "/listar")
    public ResponseEntity<List<AutorDTO>> listarAutores() {
        List<AutorDTO> listaAutores = autorService.listarAutores();
        return new ResponseEntity<>(listaAutores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> listarAutorPorId(@PathVariable Long id) {
        AutorDTO autor = autorService.listarAutorById(id);
        if (autor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(autor);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<AutorDTO> cadastrarAutor(@RequestBody AutorDTO request) {
        AutorDTO autor = autorService.cadastrarAutor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(autor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorDTO> atualizarAutor(@PathVariable Long id, @RequestBody AutorDTO request) {
        try {
            AutorDTO autorAtualizado = autorService.atualizarAutor(id, request);
            return ResponseEntity.ok(autorAtualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAutor(@PathVariable Long id) {
        try {
            autorService.deletarAutor(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

