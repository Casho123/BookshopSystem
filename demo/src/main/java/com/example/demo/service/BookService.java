package com.example.demo.service;

import com.example.demo.models.enums.AgeRestriction;

import java.io.IOException;
import java.util.List;

public interface BookService {

    void seedBooks() throws IOException;


    List<String> findAllBookTitlesWithAgeRestriction(AgeRestriction ageRestriction);
}
