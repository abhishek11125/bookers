package com.bookers.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cartId;
    @OneToOne(mappedBy = "cart",cascade = CascadeType.ALL)
    private User user;
    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();
}
