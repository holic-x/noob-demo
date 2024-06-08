package com.noob.framework.transaction.entity;

import lombok.Data;

@Data
public class TableEntity {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    public TableEntity() {
    }

    public TableEntity(String name) {
        this.name = name;
    }
}