package com.noob.base.user.model.entity;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String email;
}