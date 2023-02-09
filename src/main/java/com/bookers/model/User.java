package com.bookers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    private String name;
    private String gender;
    @Column(unique = true)
    private String email;
    private String mobile;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String role;

    //Buyer books list
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Book> buyerBooks = new ArrayList<>();

    //Buyer payment details
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Payment> buyerPayments = new ArrayList<>();

    //Author books list
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    List<Book> authorBooks = new ArrayList<>();

}
