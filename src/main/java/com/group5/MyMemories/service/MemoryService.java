package com.group5.MyMemories.service;

import org.springframework.data.domain.Page;

import com.group5.MyMemories.dto.request.MemoryRequest;
import com.group5.MyMemories.dto.response.MemoryResponse;

public interface MemoryService {

    MemoryResponse createMemory(MemoryRequest request, String email);

    Page<MemoryResponse> getAllMemories(int page, int size);

    MemoryResponse getMemory(Long id);

    MemoryResponse updateMemory(Long id, MemoryRequest request);

    void deleteMemory(Long id);
    
    Page<MemoryResponse> searchMemories(String keyword, int page, int size);

}
