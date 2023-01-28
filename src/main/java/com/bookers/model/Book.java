package com.bookers.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bookId;
    private String title;
    private String publisher;
    private LocalDate publicationDate;
    private double price;
    private Integer noOfPages;
    private String language;
    private Integer edition;

    @OneToMany(mappedBy = "book")
    private List<Author> authors = new ArrayList<>();
    @ManyToOne()
    private List<User> users = new ArrayList<>();

}
