package com.example.demo.service;

import com.example.demo.categories.BookRepository;
import com.example.demo.models.Author;
import com.example.demo.models.Book;
import com.example.demo.models.Category;
import com.example.demo.models.enums.AgeRestriction;
import com.example.demo.models.enums.EditionType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOKS_FILE_PATH = "C:\\Users\\My PC\\Desktop\\demo (1)\\demo\\src\\main\\resources\\files\\books.txt";

    private final BookRepository bookRepository;
    private final AuthorServiceImpl authorService;
    private final CategoryServiceImpl categoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorServiceImpl authorService, CategoryServiceImpl categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }


    @Override
    public void seedBooks() throws IOException {
        if (this.bookRepository.count() > 0) {
            return;
        }
        Files.readAllLines(Path.of(BOOKS_FILE_PATH))
                .forEach(row -> {
                    String[] bookInfo = row.split("\\s+");

                    Book book = createBookFromInfo(bookInfo);

                    this.bookRepository.save(book);
                });


    }

    @Override
    public List<String> findAllBookTitlesWithAgeRestriction(AgeRestriction ageRestriction) {
            return this.bookRepository.findAllByAgeRestriction(ageRestriction)
                    .stream()
                    .map(Book::getTitle).collect(Collectors.toList());
    }

    @Override
    public List<String> findAllGoldenEditionBooksWithLessThan5000Copies() {
        return this.bookRepository.findAllByEditionTypeAndCopiesLessThan(EditionType.GOLD, 5000)
                .stream()
                .map(Book::getTitle).collect(Collectors.toList());
    }

    @Override
    public List<String> findAllByPrice(BigDecimal value1  , BigDecimal value2) {

        return this.bookRepository.findAllByPriceLessThanOrGreaterThan(value1, value2)
                .stream()
                .map(res -> String.format("%s - $%.2f", res.getTitle(), res.getPrice())).collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksNotReleasedInYear(int year) {
        return this.bookRepository.findAllBooksNotReleasedInYear(year)
                .stream()
                .map(Book::getTitle).collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksReleasedBeforeDate(LocalDate date) {
        return this.bookRepository.findAllBooksByReleaseDateBefore(date)
                .stream()
                .map(book -> String.format("%s %s %.2f", book.getTitle(), book.getEditionType(), book.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksThatContainString(String input) {
        return this.bookRepository.findAllBooksThatContainString(input)
                .stream()
                .map(Book::getTitle).collect(Collectors.toList());
    }

    @Override
    public List<String> findBooksWhoseAuthorNameStartsWith(String input) {
        return this.bookRepository.findBooksWhoseAuthorNameStartsWith(input)
                .stream()
                .map(book -> String.format("%s (%s %s)", book.getTitle(), book.getAuthor().getFirstName(), book.getAuthor().getLastName()))
                .collect(Collectors.toList());
    }

    @Override
    public Long findCountOfBooksWithTitleLongerThan(int length) {
        return this.bookRepository.findBooksWithTitleLongerThan(length);
    }

    @Override
    public String findBookByTitle(String title) {
        Book book = this.bookRepository.findBookByTitle(title);
        return String.format("%s %s %s %.2f", book.getTitle(), book.getEditionType(), book.getAgeRestriction(), book.getPrice());
    }



    private Book createBookFromInfo(String[] bookInfo) {
        EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[0])];
        LocalDate releaseDate = LocalDate.parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        Integer copies = Integer.parseInt(bookInfo[2]);
        BigDecimal price = new BigDecimal(bookInfo[3]);
        AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(bookInfo[4])];
        String title = Arrays.stream(bookInfo)
                .skip(5).collect(Collectors.joining(" "));

        Author author = this.authorService.getRandomAuthor();
        Set<Category> categories = this.categoryService.getRandomCategories();

        return new Book(editionType, releaseDate, copies, price, ageRestriction, title, author, categories);


    }
}
