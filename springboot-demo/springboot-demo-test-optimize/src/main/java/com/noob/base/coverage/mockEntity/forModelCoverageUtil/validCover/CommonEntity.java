package com.noob.base.coverage.mockEntity.forModelCoverageUtil.validCover;

import java.time.LocalDate;
import java.util.Objects;

/**
 * CommonEntity 实体类：普通实体类定义
 */
public class CommonEntity {
    // 成员变量
    private Long id;            // 唯一标识
    private String name;        // 姓名
    private Integer age;        // 年龄
    private String gender;      // 性别
    private LocalDate birthday;// 出生日期
    private String email;       // 电子邮箱
    private String phone;       // 电话号码
    private String address;     // 住址

    // 构造方法

    /**
     * 无参构造方法
     */
    public CommonEntity() {
    }

    /**
     * 带参数的构造方法
     *
     * @param name 姓名
     * @param age  年龄
     */
    public CommonEntity(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    /**
     * 全参数构造方法
     */
    public CommonEntity(Long id, String name, Integer age, String gender,
                        LocalDate birthday, String email, String phone, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    // Getter 和 Setter 方法

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // 重写 equals 和 hashCode 方法

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommonEntity person = (CommonEntity) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(name, person.name) &&
                Objects.equals(age, person.age) &&
                Objects.equals(gender, person.gender) &&
                Objects.equals(birthday, person.birthday) &&
                Objects.equals(email, person.email) &&
                Objects.equals(phone, person.phone) &&
                Objects.equals(address, person.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, gender, birthday, email, phone, address);
    }

    // 重写 toString 方法

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}