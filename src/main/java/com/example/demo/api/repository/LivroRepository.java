package com.example.demo.api.repository;

import com.example.demo.api.model.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<LivroEntity, Long> {
}
