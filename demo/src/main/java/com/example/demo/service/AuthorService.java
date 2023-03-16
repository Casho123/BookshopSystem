package com.example.demo.service;

import com.example.demo.models.Author;

import java.io.IOException;

public interface AuthorService {

    void seedAuthors() throws IOException;

    Author getRandomAuthor();
}
