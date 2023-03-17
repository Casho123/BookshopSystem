package com.example.demo;

import com.example.demo.service.AuthorServiceImpl;
import com.example.demo.service.BookServiceImpl;
import com.example.demo.service.CategoryServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;

@Component
public class CommandLinerRunnerImpl implements CommandLineRunner {


    private final CategoryServiceImpl categoryService;
    private final AuthorServiceImpl authorService;
    private final BookServiceImpl bookService;
    private final BufferedReader bufferedReader;

    public CommandLinerRunnerImpl(CategoryServiceImpl categoryService, AuthorServiceImpl authorService, BookServiceImpl bookService, BufferedReader bufferedReader) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();


    }

    private void seedData() throws IOException {
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();
    }
}
