package com.example.demo.service;

import com.example.demo.categories.BookRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOKS_FILE_PATH = "C:\\Users\\My PC\\Desktop\\demo (1)\\demo\\src\\main\\resources\\files\\books.txt";

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public void seedBooks() throws IOException {
        if (this.bookRepository.count() > 0) {
            return;
        }
        Files.readAllLines(Path.of(BOOKS_FILE_PATH))
                .stream()
                .filter(row -> !row.isEmpty());


    }
}
