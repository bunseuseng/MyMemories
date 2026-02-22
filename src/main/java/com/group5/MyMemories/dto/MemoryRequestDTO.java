package com.group5.MyMemories.dto;

import lombok.Data;

@Data
public class MemoryRequestDTO {
    private String title;
    private String content;
    private Long categoryId;
}
