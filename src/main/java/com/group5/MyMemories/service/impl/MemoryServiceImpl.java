package com.group5.MyMemories.service.impl;

import com.group5.MyMemories.dto.request.MemoryRequest;
import com.group5.MyMemories.dto.response.MemoryResponse;
import com.group5.MyMemories.entity.Memory;
import com.group5.MyMemories.entity.MemoryImageEntity;
import com.group5.MyMemories.entity.SeedCategory;
import com.group5.MyMemories.repositories.MemoryRepository;
import com.group5.MyMemories.repositories.SeedCategoriesRepo;
import com.group5.MyMemories.service.MemoryService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MemoryServiceImpl implements MemoryService {

    private final MemoryRepository memoryRepository;
    private final SeedCategoriesRepo categoryRepository;

    public MemoryServiceImpl(MemoryRepository memoryRepository,
                             SeedCategoriesRepo categoryRepository) {
        this.memoryRepository = memoryRepository;
        this.categoryRepository = categoryRepository;
    }

    // ✅ CREATE (with email ready for future user logic)
    @Override
    public MemoryResponse createMemory(MemoryRequest request, String email) {

        Memory memory = new Memory();
        memory.setTitle(request.getTitle());
        memory.setDescription(request.getDescription());
        memory.setCreatedAt(LocalDateTime.now());
        memory.setUpdatedAt(LocalDateTime.now());

        // category already seeded
        SeedCategory category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        memory.setCategory(category);

        // images
        List<MemoryImageEntity> images = request.getImageUrls().stream()
                .map(url -> {
                    MemoryImageEntity img = new MemoryImageEntity();
                    img.setImageUrl(url);
                    img.setMemory(memory);
                    return img;
                }).toList();

        memory.setImages(images);

        Memory saved = memoryRepository.save(memory);
        return mapToResponse(saved);
    }

    // ✅ GET ONE
    @Override
    public MemoryResponse getMemory(Long id) {
        Memory memory = memoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Memory not found"));
        return mapToResponse(memory);
    }

    // ✅ GET ALL WITH PAGINATION
    @Override
    public Page<MemoryResponse> getAllMemories(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return memoryRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    // ✅ UPDATE
    @Override
    public MemoryResponse updateMemory(Long id, MemoryRequest request) {

        Memory memory = memoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Memory not found"));

        memory.setTitle(request.getTitle());
        memory.setDescription(request.getDescription());
        memory.setUpdatedAt(LocalDateTime.now());

        if (request.getCategoryId() != null) {
            SeedCategory category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            memory.setCategory(category);
        }

        // replace images
        memory.getImages().clear();

        List<MemoryImageEntity> images = request.getImageUrls().stream()
                .map(url -> {
                    MemoryImageEntity img = new MemoryImageEntity();
                    img.setImageUrl(url);
                    img.setMemory(memory);
                    return img;
                }).toList();

        memory.setImages(images);

        return mapToResponse(memoryRepository.save(memory));
    }

    // ✅ DELETE
    @Override
    public void deleteMemory(Long id) {
        Memory memory = memoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Memory not found"));
        memoryRepository.delete(memory);
    }

    // ✅ MAPPER
    private MemoryResponse mapToResponse(Memory memory) {
        MemoryResponse response = new MemoryResponse();
        response.setId(memory.getId());
        response.setTitle(memory.getTitle());
        response.setDescription(memory.getDescription());
        response.setCreatedAt(memory.getCreatedAt());
        response.setCategoryName(
                memory.getCategory() != null ? memory.getCategory().getName() : null
        );
        response.setEmail(
                memory.getUser() != null ? memory.getUser().getEmail() : null
        );
        response.setImageUrls(
                memory.getImages().stream()
                        .map(MemoryImageEntity::getImageUrl)
                        .toList()
        );
        return response;
    }
    
    @Override
    public Page<MemoryResponse> searchMemories(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return memoryRepository
                .findByTitleContainingIgnoreCaseOrCategory_NameContainingIgnoreCase(keyword, keyword, pageable)
                .map(this::mapToResponse);
    }

}
