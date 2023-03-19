package com.example.demo;

import com.example.demo.models.enums.AgeRestriction;
import com.example.demo.service.AuthorServiceImpl;
import com.example.demo.service.BookServiceImpl;
import com.example.demo.service.CategoryServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

        System.out.println("Select exercise number:");
        int exerciseNumber = Integer.parseInt(bufferedReader.readLine());

        switch (exerciseNumber) {
            case 1:
                bookTitlesByAgeRestriction();
                break;
            case 2:
                goldenEditionBooksWithLessThan5000Copies();
                break;
            case 3:
                booksByPrice();
                break;
            case 4:
                booksNotReleasedInYear();
                break;
            case 5:
                booksReleasedBeforeDate();
                break;
            case 6:
                authorsNameEndsWith();
                break;
            case 7:
                booksThatContainString();
            case 8:
                booksWhoseAuthorNameStartsWith();
                break;
            case 9:
                booksWithTitleLongerThan();
                break;
            case 10:
                getTotalBookCopies();
                break;
            case 11:
                getReducedBook();
                break;
        }

    }

    private void getReducedBook() throws IOException {
        String title = bufferedReader.readLine();;
        System.out.println(this.bookService.findBookByTitle(title));
    }

    private void getTotalBookCopies() {
        this.authorService.findAllAuthorsAndTotalBookCopies().forEach(System.out::println);

    }

    private void booksWithTitleLongerThan() throws IOException {
        int length = Integer.parseInt(bufferedReader.readLine());
        System.out.println(this.bookService.findCountOfBooksWithTitleLongerThan(length));;
    }

    private void booksWhoseAuthorNameStartsWith() throws IOException {
            String input = bufferedReader.readLine();
            this.bookService.findBooksWhoseAuthorNameStartsWith(input)
                    .forEach(System.out::println);
    }

    private void booksThatContainString() throws IOException {
        String input = bufferedReader.readLine();
        this.bookService.findAllBooksThatContainString(input)
                .forEach(System.out::println);
    }

    private void authorsNameEndsWith() throws IOException {
        String input = bufferedReader.readLine();
        this.authorService.findAllAuthorsWhoseNameEndsWith(input)
                .forEach(System.out::println);
    }

    private void booksReleasedBeforeDate() throws IOException {
        String input = bufferedReader.readLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
        LocalDate date = LocalDate.parse(input, formatter);

        this.bookService.findAllBooksReleasedBeforeDate(date)
                .forEach(System.out::println);
    }

    private void booksNotReleasedInYear() {
        this.bookService.findAllBooksNotReleasedInYear(2000)
                .forEach(System.out::println);
    }

    private void booksByPrice() {
        this.bookService.findAllByPrice(BigDecimal.valueOf(5), BigDecimal.valueOf(40))
                .forEach(System.out::println);
    }

    private void goldenEditionBooksWithLessThan5000Copies() {
        this.bookService
                .findAllGoldenEditionBooksWithLessThan5000Copies()
                .forEach(System.out::println);
    }

    private void bookTitlesByAgeRestriction() throws IOException {
        System.out.println("Enter age restriction:");
        AgeRestriction ageRestriction = AgeRestriction.valueOf(bufferedReader.readLine().toUpperCase());

        this.bookService.findAllBookTitlesWithAgeRestriction(ageRestriction).forEach(System.out::println);

    }

    private void seedData() throws IOException {
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();
    }
}
