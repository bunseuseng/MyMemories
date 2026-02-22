package com.group5.MyMemories.service.impl;

import com.group5.MyMemories.dto.MemoryRequestDTO;
import com.group5.MyMemories.dto.MemoryResponseDTO;
import com.group5.MyMemories.entity.Category;
import com.group5.MyMemories.entity.Memory;
import com.group5.MyMemories.entity.User;
import com.group5.MyMemories.repositories.CategoryRepository;
import com.group5.MyMemories.repositories.MemoryRepository;
import com.group5.MyMemories.repositories.UserRepository;
import com.group5.MyMemories.service.MemoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemoryServiceImpl implements MemoryService {

    private final MemoryRepository memoryRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    private void ensureOwner(Long userId, Memory memory) {
        if (!memory.getUser().getId().equals(userId)) {
            throw new RuntimeException("You are not allowed to access this memory!");
        }
    }

    @Override
    public MemoryResponseDTO createMemory(Long userId, MemoryRequestDTO dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Memory memory = new Memory();
        memory.setTitle(dto.getTitle());
        memory.setContent(dto.getContent());
        memory.setCategory(category);
        memory.setUser(user);

        memoryRepository.save(memory);

        return convertToDTO(memory);
    }

    @Override
    public MemoryResponseDTO getMemoryById(Long userId, Long memoryId) {
        Memory memory = memoryRepository.findById(memoryId)
                .orElseThrow(() -> new RuntimeException("Memory not found"));
        
        ensureOwner(userId, memory);

        return convertToDTO(memory);
    }

    @Override
    public List<MemoryResponseDTO> getAllMemories(Long userId) {
        return memoryRepository.findByUserId(userId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MemoryResponseDTO updateMemory(Long userId, Long memoryId, MemoryRequestDTO dto) {
        Memory memory = memoryRepository.findById(memoryId)
                .orElseThrow(() -> new RuntimeException("Memory not found"));

        ensureOwner(userId, memory);

        memory.setTitle(dto.getTitle());
        memory.setContent(dto.getContent());

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        memory.setCategory(category);

        memoryRepository.save(memory);

        return convertToDTO(memory);
    }

    @Override
    public void deleteMemory(Long userId, Long memoryId) {
        Memory memory = memoryRepository.findById(memoryId)
                .orElseThrow(() -> new RuntimeException("Memory not found"));

        ensureOwner(userId, memory);

        memoryRepository.delete(memory);
    }

    private MemoryResponseDTO convertToDTO(Memory memory) {
        MemoryResponseDTO dto = new MemoryResponseDTO();
        dto.setId(memory.getId());
        dto.setTitle(memory.getTitle());
        dto.setContent(memory.getContent());
        dto.setCategoryName(memory.getCategory().getName());
        dto.setCreatedAt(memory.getCreatedAt().toString());
        return dto;
    }
}
