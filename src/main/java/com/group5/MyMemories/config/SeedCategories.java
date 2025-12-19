package com.group5.MyMemories.config;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.group5.MyMemories.entity.SeedCategory;
import com.group5.MyMemories.repositories.SeedCategoriesRepo;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SeedCategories implements CommandLineRunner {

    private final SeedCategoriesRepo seedCategoriesRepo;

    @Override
    public void run(String... args) {

        List<String> defaultCategories = List.of(
                "Personal",
                "Travel",
                "Work",
                "Family",
                "Friends",
                "Study",
                "Hobby",
                "Important",
                "Health",
                "Daily Life",
                "Special Events",
                "Other"
        );

        for (String categoryName : defaultCategories) {
            if (!seedCategoriesRepo.existsByName(categoryName)) {
                // ✅ create entity, not some other class
                SeedCategory category = new SeedCategory();
                category.setName(categoryName);
                seedCategoriesRepo.save(category);
            }
        }

        System.out.println("🧠 Memory categories inserted successfully!");
    }
}
