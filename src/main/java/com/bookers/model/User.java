package com.bookers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
    @NotBlank(message = "Name field can not be blank")
    private String name;
    @NotBlank(message = "Gender field can not be blank")
    private String gender;
    @Column(unique = true)
    @Email
    @NotBlank(message = "Email can not be blank")
    private String email;
    @Size(min = 10,max = 10,message = "Please provide minimum 10 digit mobile number")
    @NotBlank(message = "Mobile can not be blank")
    private String mobile;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{5,15}$",message = "Password must be of min 5 and max 15 character and includes at least one uppercase,lowercase,symbols and numbers from 0 to 9")
    private String password;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    private Cart cart;

    @NotBlank(message = "Role can not be blank")
    private String role;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    //Buyer books list
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Book> buyerBooks = new ArrayList<>();

    //Buyer payment details
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Payment> buyerPayments = new ArrayList<>();

    //Author books list
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    List<Book> authorBooks = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();



}
