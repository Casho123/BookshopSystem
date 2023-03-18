package com.example.demo.service;

import com.example.demo.models.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {

    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    List<String> findAllAuthorsWhoseNameEndsWith(String e);
}
