package com.group5.MyMemories.dto.request;

import java.util.List;

import lombok.Data;

@Data
public class MemoryRequest {
    private String title;
    private String description;
    private Long categoryId; // choose from seeded categories
    private List<String> imageUrls; // multiple images

}
