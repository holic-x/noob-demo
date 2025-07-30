package com.noob.base.coverage.mockEntity.validCover;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

// @EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LombokEntityBySuperBuilderAnnotation extends ParentLombokEntity {
    protected String name;
    protected int age;
}

// 父类定义
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
class ParentLombokEntity {
    private String department;
    private double salary;
}

