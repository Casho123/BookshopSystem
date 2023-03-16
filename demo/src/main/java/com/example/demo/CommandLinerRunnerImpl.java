package com.example.demo;

import com.example.demo.service.AuthorServiceImpl;
import com.example.demo.service.CategoryServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLinerRunnerImpl implements CommandLineRunner {


    private final CategoryServiceImpl categoryService;
    private final AuthorServiceImpl authorService;

    public CommandLinerRunnerImpl(CategoryServiceImpl categoryService, AuthorServiceImpl authorService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
    }

    @Override
    public void run(String... args) throws Exception {

        this.categoryService.seedCategories();
        this.authorService.seedAuthors();



    }
}
