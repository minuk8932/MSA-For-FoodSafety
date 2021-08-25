package com.example.repository;

import com.example.model.Nutrients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpenApiRepository extends JpaRepository<Nutrients, Long> {
}
