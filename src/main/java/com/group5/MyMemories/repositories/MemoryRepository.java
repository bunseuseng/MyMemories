package com.group5.MyMemories.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.group5.MyMemories.entity.Memory;

public interface MemoryRepository extends JpaRepository<Memory, Long> {
	List<Memory> findByUserId(Long userId);
	List<Memory> findByCategoryId(Long categoryId);
	
	// Search by title containing keyword (fuzzy)
    Page<Memory> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    // Search by category name containing keyword
    Page<Memory> findByCategory_NameContainingIgnoreCase(String categoryName, Pageable pageable);

    // Combined: title OR category
    Page<Memory> findByTitleContainingIgnoreCaseOrCategory_NameContainingIgnoreCase(
            String title, String categoryName, Pageable pageable
    );

}
