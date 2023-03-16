package com.example.demo.service;

import com.example.demo.categories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class AuthorServiceImpl implements AuthorService {

    private static final String AUTHORS_FILE_PATH = "C:\\Users\\My PC\\Desktop\\demo (1)\\demo\\src\\main\\resources\\files\\authors.txt";

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Override
    public void seedAuthors() throws IOException {
        if (authorRepository.count() > 0) {
            return;
        }
        Files.readAllLines(Path.of(AUTHORS_FILE_PATH))
                .stream()
                .filter(row -> !row.isEmpty())
                .forEach();

    }
}
