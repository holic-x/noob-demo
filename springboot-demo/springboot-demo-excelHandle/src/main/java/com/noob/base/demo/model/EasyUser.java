package com.noob.base.demo.model;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户实体类定义
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EasyUser {
    @ExcelProperty("用户ID")
    private Long id;

    @ExcelProperty("用户名")
    private String username;

    @ExcelProperty("年龄")
    private Integer age;

    // 省略getter和setter

}
