package com.noob.base.coverage.mockEntity.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Employee 实体类
 * 继承 Person 并添加员工特有属性
 * 使用 Lombok 简化代码
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Employee extends Person {
    // 员工特有属性
    private String employeeId;       // 员工编号
    private String department;       // 部门
    private String position;        // 职位
    private BigDecimal salary;      // 薪资
    private LocalDate hireDate;     // 入职日期
    private String managerId;       // 直属领导ID
    private EmploymentStatus status; // 雇佣状态

    // 雇佣状态枚举
    public enum EmploymentStatus {
        ACTIVE,         // 在职
        ON_LEAVE,       // 休假中
        TERMINATED,     // 已离职
        RETIRED         // 已退休
    }

    // 构造方法
    public Employee() {
        super();
    }

    // 带基础信息的构造方法
    public Employee(String name, Integer age, String employeeId, String department) {
        super(name, age);
        this.employeeId = employeeId;
        this.department = department;
    }

    // 全参数构造方法
    @Builder
    public Employee(Long id, String name, Integer age, String gender, LocalDate birthday,
                    String email, String phone, String address, String employeeId,
                    String department, String position, BigDecimal salary,
                    LocalDate hireDate, String managerId, EmploymentStatus status) {
        super(id, name, age, gender, birthday, email, phone, address);
        this.employeeId = employeeId;
        this.department = department;
        this.position = position;
        this.salary = salary;
        this.hireDate = hireDate;
        this.managerId = managerId;
        this.status = status;
    }
}