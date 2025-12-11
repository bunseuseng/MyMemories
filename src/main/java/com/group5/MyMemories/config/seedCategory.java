package com.group5.MyMemories.config;

import com.group5.MyMemories.entity.Category;
import com.group5.MyMemories.repositories.CategoryRepoitories;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class seedCategory {

    @Bean
    CommandLineRunner initCategories(CategoryRepoitories categoryRepositories) {
        return args -> {

            String[] defaultCategories = {"Travel", "Food", "Work", "Personal", "Fitness"};

            for (String name : defaultCategories) {
                categoryRepositories.findByName(name)
                        .orElseGet(() -> categoryRepositories.save(new Category(null, name)));
            }

            System.out.println("Categories seeded!");
        };
    }
}
