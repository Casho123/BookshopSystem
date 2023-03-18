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
}
