package com.bookers.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Integer quantity;
    private String publisher;
    private LocalDate publicationDate;
    private Integer noOfPages;
    private String language;
    private Integer edition;
    private String literatureType;
    private double price;
    @JsonIgnore(value = false)
    private String authorName;

    @ManyToOne
    private User user;

    @ManyToOne
    private Payment payment;

}
