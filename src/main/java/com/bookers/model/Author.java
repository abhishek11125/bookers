package com.bookers.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer authorId;
    private String authorName;
    private String gender;
    private String email;
    private String mobile;
    private String password;
   @OneToMany(mappedBy = "author")
    private List<Book> books = new ArrayList<>();

    public Author(String authorName, String gender, String email, String mobile, String password) {
        this.authorName = authorName;
        this.gender = gender;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
    }
}
