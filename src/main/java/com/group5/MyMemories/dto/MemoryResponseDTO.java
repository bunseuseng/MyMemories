package com.group5.MyMemories.dto;

import lombok.Data;

@Data
public class MemoryResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String categoryName;
    private String createdAt;
}
