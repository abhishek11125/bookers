package com.bookers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Customer {
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
//    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{5,30}$",message = "Password must be of min 5 and max 15 character and includes at least one uppercase,lowercase,symbols and numbers from 0 to 9")
    @NotBlank(message = "Please provide password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;

    @NotBlank(message = "Role can not be blank")
    private String role;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    //Buyer payment details
    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Payment> buyerPayments = new ArrayList<>();

    @OneToOne
    @JsonIgnore
    private Order order;

}
