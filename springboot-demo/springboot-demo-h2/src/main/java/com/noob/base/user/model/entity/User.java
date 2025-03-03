package com.noob.base.user.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "noob_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
}