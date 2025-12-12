package com.group5.MyMemories.controller;

import com.group5.MyMemories.dto.MemoryRequestDTO;
import com.group5.MyMemories.dto.MemoryResponseDTO;
import com.group5.MyMemories.service.MemoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memories")
@RequiredArgsConstructor
public class MemoryController {

    private final MemoryService memoryService;

    // Assume you get userId from JWT token later
    private Long getLoggedInUserId() {
        return 1L; // Temporary — replace with JWT later
    }

    @PostMapping("/create")
    public MemoryResponseDTO createMemory(@RequestBody MemoryRequestDTO dto) {
        return memoryService.createMemory(getLoggedInUserId(), dto);
    }

    @GetMapping("/{id}")
    public MemoryResponseDTO getMemory(@PathVariable Long id) {
        return memoryService.getMemoryById(getLoggedInUserId(), id);
    }

    @GetMapping
    public List<MemoryResponseDTO> getAllMemories() {
        return memoryService.getAllMemories(getLoggedInUserId());
    }

    @PutMapping("/{id}")
    public MemoryResponseDTO updateMemory(
            @PathVariable Long id,
            @RequestBody MemoryRequestDTO dto
    ) {
        return memoryService.updateMemory(getLoggedInUserId(), id, dto);
    }

    @DeleteMapping("/{id}")
    public String deleteMemory(@PathVariable Long id) {
        memoryService.deleteMemory(getLoggedInUserId(), id);
        return "Memory deleted successfully!";
    }
}
