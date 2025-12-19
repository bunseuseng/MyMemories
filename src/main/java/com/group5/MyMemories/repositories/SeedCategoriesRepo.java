package com.group5.MyMemories.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group5.MyMemories.entity.SeedCategory;

public interface SeedCategoriesRepo extends JpaRepository<SeedCategory, Long> {
    boolean existsByName(String name);
}