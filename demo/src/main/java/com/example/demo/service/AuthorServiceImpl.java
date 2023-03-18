package com.example.demo.service;

import com.example.demo.categories.AuthorRepository;
import com.example.demo.models.Author;
import com.example.demo.models.Book;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

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
                .forEach(rec -> {
                    String[] arr = rec.split("\\s+");
                    String fName = arr[0];
                    String lName = arr[1];
                    Author author = new Author(fName, lName);
                    this.authorRepository.save(author);

                });


    }

    @Override
    public Author getRandomAuthor() {
        long randomId = ThreadLocalRandom.current().nextLong(1, this.authorRepository.count() + 1);

        return this.authorRepository.findById(randomId).orElse(null);
    }

    @Override
    public List<String> findAllAuthorsWhoseNameEndsWith(String letter) {
        return this.authorRepository.findAllWhereFirstNameEndsWith(letter)
                .stream()
                .map(author -> String.format("%s %s", author.getFirstName(), author.getLastName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllAuthorsAndTotalBookCopies() {
        List<String> result = this.authorRepository.findAll()
                .stream()
                .map(author -> String.format("%s %s - %d", author.getFirstName(), author.getLastName(),
                        author.getBooks().stream()
                                .map(Book::getCopies)
                                .reduce(Integer::sum).orElse(null))).collect(Collectors.toList());


    }


}
