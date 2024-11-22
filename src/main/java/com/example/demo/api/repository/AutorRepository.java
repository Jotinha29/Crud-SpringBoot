package com.example.demo.api.repository;

import com.example.demo.api.model.AutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<AutorEntity, Long> {
}

