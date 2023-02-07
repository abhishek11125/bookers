package com.bookers.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCurrentSession {
    @Id
    @Column(unique = true)
    private Integer userId;
    private String uid;
    private LocalDateTime time;

}
