package com.example.usersvc.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Token extends BaseModel {
    private String value;
    private Date expiryAt;
    // Many users can belong to 1 user
    @ManyToOne
    private User user;
}
