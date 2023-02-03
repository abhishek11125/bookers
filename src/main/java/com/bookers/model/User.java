package com.bookers.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

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
    @Column(unique = true)
    private String email;
    private String mobile;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String role;

//    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
//    private List<Book> books = new ArrayList<>();
//    //wishlist
//    private Stack<Book> wishList = new Stack<>();
//    //payment details
//    private List<Payment> payments = new ArrayList<>();

    public User(String userName, String gender, String email, String mobile, String password) {
        this.userName = userName;
        this.gender = gender;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
    }
}
