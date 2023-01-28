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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    private String userName;
    private String gender;
    private String email;
    private String mobile;
    private String password;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

    public User(String userName, String gender, String email, String mobile, String password) {
        this.userName = userName;
        this.gender = gender;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
    }
}
