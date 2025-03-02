package com.noob.base.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户实体类定义
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoiUser {

    private String name;

    private Integer age;

    private String email;

    // 省略getter和setter

}
