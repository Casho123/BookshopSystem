package com.example.demo.service;

import com.example.demo.categories.CategoryRepository;
import com.example.demo.models.Category;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final String CATEGORIES_FILE_PATH = "C:\\Users\\My PC\\Desktop\\demo (1)\\demo\\src\\main\\resources\\files\\categories.txt";


    private final CategoryRepository categoryRepository;


    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void seedCategories() throws IOException {

        if (categoryRepository.count() > 0) {
            return;
        }

        Files.readAllLines(Path.of(CATEGORIES_FILE_PATH))
                .stream()
                .filter(row -> !row.isEmpty())
                .forEach(categoryName-> {
                    Category category = new Category(categoryName);
                    this.categoryRepository.save(category);
                });



    }
}

