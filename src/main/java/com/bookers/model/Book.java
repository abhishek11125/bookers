package com.bookers.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    private Integer noOfPages;
    private String language;
    private Integer edition;
    private String literatureType;
    private double price;

    @ManyToOne(cascade = CascadeType.ALL)
    private Author author;
    @ManyToOne
    private List<User> users = new ArrayList<>();

}
