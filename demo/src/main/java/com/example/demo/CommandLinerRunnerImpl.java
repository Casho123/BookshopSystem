package com.example.demo;

import com.example.demo.service.AuthorServiceImpl;
import com.example.demo.service.BookServiceImpl;
import com.example.demo.service.CategoryServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class CommandLinerRunnerImpl implements CommandLineRunner {


    private final CategoryServiceImpl categoryService;
    private final AuthorServiceImpl authorService;

    private final BookServiceImpl bookService;

    public CommandLinerRunnerImpl(CategoryServiceImpl categoryService, AuthorServiceImpl authorService, BookServiceImpl bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    private void seedData() throws IOException {
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();
    }
}
