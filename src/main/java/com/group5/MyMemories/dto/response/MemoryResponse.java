package com.group5.MyMemories.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MemoryResponse {
	private Long id;
    private String title;
    private String description;
    private String categoryName;
    private String email;
    private LocalDateTime createdAt;
    private List<String> imageUrls; // return multiple images

}
