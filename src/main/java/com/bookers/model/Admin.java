package com.bookers.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer adminId;
    private String adminName;
    private String email;
    private String password;

    public Admin(String adminName, String email, String password) {
        this.adminName = adminName;
        this.email = email;
        this.password = password;
    }
}
