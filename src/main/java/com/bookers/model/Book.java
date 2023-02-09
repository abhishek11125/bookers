package com.bookers.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotBlank(message = "Title can not be blank")
    private String title;
    @NotNull(message = "Quantity can not be null")
    @Min(value = 0,message = "Quantity can not be in negative number")
    private Integer quantity;
    @NotBlank(message = "Publisher can not be blank")
    private String publisher;
    @NotNull(message = "Date can not be null and should follow format YYYY/MM/DD ")
    private LocalDate publicationDate;
    @NotNull(message = "Please provide number of pages")
    @Min(value = 0,message = "Number of pages can not be negative")
    private Integer noOfPages;
    @NotBlank(message = "Language can not be blank")
    private String language;
    @NotNull(message = "Please provide edition number")
    @Min(value = 0,message = "Edition number can not be negative")
    private Integer edition;
    @NotBlank(message = "Literature type can not be blank")
    private String literatureType;
    @NotNull(message = "Please provide price for book")
    @Min(value = 0,message = "Price can not be negative")
    private double price;
    @JsonIgnore(value = false)
    private String authorName;

    @ManyToOne
    private User user;

    @ManyToOne
    private Payment payment;

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Order order;

}
