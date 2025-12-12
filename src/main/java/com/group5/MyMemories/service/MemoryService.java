package com.group5.MyMemories.service;

import com.group5.MyMemories.dto.MemoryRequestDTO;
import com.group5.MyMemories.dto.MemoryResponseDTO;

import java.util.List;

public interface MemoryService {

    MemoryResponseDTO createMemory(Long userId, MemoryRequestDTO dto);

    MemoryResponseDTO getMemoryById(Long userId, Long memoryId);

    List<MemoryResponseDTO> getAllMemories(Long userId);

    MemoryResponseDTO updateMemory(Long userId, Long memoryId, MemoryRequestDTO dto);

    void deleteMemory(Long userId, Long memoryId);

}
