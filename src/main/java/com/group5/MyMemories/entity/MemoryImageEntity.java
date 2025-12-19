package com.group5.MyMemories.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "memory_image")
@Data
public class MemoryImageEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String imageUrl;
	
	@ManyToOne
	@JoinColumn(name = "memory_id")
	private Memory memory;
}
