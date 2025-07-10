package com.noob.base.coverage.mockEntity.validCover;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LombokEntityByBuilderAnnotation {
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
