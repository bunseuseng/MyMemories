package com.group5.MyMemories.repositories;

import com.group5.MyMemories.entity.MemoryImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoryImageRepository extends JpaRepository<MemoryImageEntity, Long> {
}
