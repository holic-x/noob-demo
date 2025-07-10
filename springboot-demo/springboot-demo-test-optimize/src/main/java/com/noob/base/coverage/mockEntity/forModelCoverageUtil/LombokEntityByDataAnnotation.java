package com.noob.base.coverage.mockEntity.forModelCoverageUtil;

import lombok.Data;

import java.time.LocalDate;

/**
 * 基于Lombok注解修饰的实体定义
 */
@Data
public class LombokEntityByDataAnnotation {
    // 成员变量
    private Long id;            // 唯一标识
    private String name;        // 姓名
    private Integer age;        // 年龄
    private String gender;      // 性别
    private LocalDate birthday;// 出生日期
    private String email;       // 电子邮箱
    private String phone;       // 电话号码
    private String address;     // 住址
}
