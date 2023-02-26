package com.bookers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer addressId;
    @NotBlank(message = "Please provide street number")
    private String streetNo;
    @NotBlank(message = "Please provide building name")
    private String buildingName;
    @NotBlank(message = "Please provide city name")
    private String city;
    @NotBlank(message = "Please provide state name")
    private String state;
    @NotBlank(message = "Please provide country name")
    private String country;
    @NotBlank(message = "Please provide pin code")
    private String pinCode;

    @ManyToOne
    @JsonIgnore
    private Customer customer;

    @OneToMany(mappedBy = "address")
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

}
