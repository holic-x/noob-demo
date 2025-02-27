package com.noob.base.demo.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "noob_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
}