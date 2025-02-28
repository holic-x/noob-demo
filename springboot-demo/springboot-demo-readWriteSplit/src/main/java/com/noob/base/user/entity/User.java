package com.noob.base.user.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "noob_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // getters and setters
}