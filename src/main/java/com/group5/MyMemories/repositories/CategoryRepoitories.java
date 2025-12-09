package com.group5.MyMemories.repositories;

import com.group5.MyMemories.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepoitories extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
