package com.example.demo.models;

import com.example.demo.models.enums.EditionType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "books")
public class Book extends BaseEntity {

    private String title;
    private String description;
    private EditionType editionType;
    private BigDecimal price;


}
