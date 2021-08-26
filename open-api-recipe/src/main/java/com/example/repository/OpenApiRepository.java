package com.example.repository;

import com.example.model.Recipes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpenApiRepository extends JpaRepository<Recipes, Long> {
}
