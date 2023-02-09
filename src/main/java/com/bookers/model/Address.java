package com.bookers.model;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer addressId;
    private String streetNo;
    private String buildingName;
    private String city;
    private String state;
    private String country;
    private String pinCode;
    @ManyToOne
    private User user;

}
