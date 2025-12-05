package com.group5.MyMemories.entity;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "memories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Memory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
