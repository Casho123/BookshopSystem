package com.example.demo.categories;

import com.example.demo.models.Book;
import com.example.demo.models.enums.AgeRestriction;
import com.example.demo.models.enums.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);
    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int count);
    @Query("SELECT b FROM Book b WHERE b.price < :price1 OR b.price > :price2")
    List<Book> findAllByPriceLessThanOrGreaterThan(BigDecimal price1, BigDecimal price2);

    @Query("SELECT b FROM Book b WHERE EXTRACT(YEAR FROM b.releaseDate) != :year")
    List<Book> findAllBooksNotReleasedInYear(int year);

    List<Book> findAllBooksByReleaseDateBefore(LocalDate date);

    @Query("SELECT b FROM Book b WHERE b.title LIKE %:input%")
    List<Book> findAllBooksThatContainString(String input);

    @Query("SELECT b FROM Book b JOIN Author a ON b.author = a.id WHERE a.lastName LIKE :input%")
    List<Book> findBooksWhoseAuthorNameStartsWith(String input);
}
