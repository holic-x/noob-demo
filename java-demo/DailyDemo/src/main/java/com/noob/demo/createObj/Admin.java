package com.noob.demo.createObj;

import lombok.Data;

import java.io.Serializable;

@Data
public class Admin implements Serializable {

    private String name;

    private static final long serialVersionUID = 1L;

}
