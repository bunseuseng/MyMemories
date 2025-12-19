package com.group5.MyMemories.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "memories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Memory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;
    
    private LocalDateTime createdAt;  // ✅ this must exist
    private LocalDateTime updatedAt;  // ✅ this must exist

   
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    // Many memories belong to one category
    @ManyToOne
    @JoinColumn(name = "category_id")
    private SeedCategory category;
    
    @OneToMany(mappedBy = "memory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemoryImageEntity> images;

}
