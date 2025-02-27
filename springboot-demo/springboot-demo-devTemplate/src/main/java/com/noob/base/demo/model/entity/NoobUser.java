package com.noob.base.demo.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class NoobUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
}