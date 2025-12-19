package com.group5.MyMemories.controller;

import com.group5.MyMemories.dto.request.MemoryRequest;
import com.group5.MyMemories.dto.response.MemoryResponse;
import com.group5.MyMemories.service.MemoryService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/memory")
@RequiredArgsConstructor
public class MemoryController {

    private final MemoryService memoryService;

    @PostMapping
    public ResponseEntity<MemoryResponse> createMemory(@RequestBody MemoryRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName(); // email comes from JWT
        MemoryResponse response = memoryService.createMemory(request, email);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public Page<MemoryResponse> getAllMemories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return memoryService.getAllMemories(page, size);
    }
    
    @GetMapping("/id")
    public ResponseEntity<MemoryResponse> getMapping(@PathVariable Long id) {
    	return ResponseEntity.ok(memoryService.getMemory(id));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<MemoryResponse> updateMemory(@PathVariable Long id,
                                                       @RequestBody MemoryRequest request) {
        return ResponseEntity.ok(memoryService.updateMemory(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemory(@PathVariable Long id) {
        memoryService.deleteMemory(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/search")
    public Page<MemoryResponse> searchMemories(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return memoryService.searchMemories(keyword, page, size);
    }

 }
