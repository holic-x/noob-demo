package com.noob.base.qrcode.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name="noob_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    // Getters and Setters
}