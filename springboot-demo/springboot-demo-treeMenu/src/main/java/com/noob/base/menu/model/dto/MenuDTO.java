package com.noob.base.menu.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class MenuDTO {
    private Long id;
    private String name;
    private List<MenuDTO> children;

    // Getters and Setters
}