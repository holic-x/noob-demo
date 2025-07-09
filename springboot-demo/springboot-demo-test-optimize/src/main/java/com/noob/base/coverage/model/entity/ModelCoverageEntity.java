package com.noob.base.coverage.model.entity;

import java.util.Objects;

/**
 * ModelCoverageEntity(实体)：
 * 用于ModelCoverageUtil测试覆盖验证，定义各种场景的实体属性、方法（构造器）等场景测试
 */
public class ModelCoverageEntity {
    private Long id;
    private String name;
    private int age;

    // param 参数
    private String param;

    // 无参构造器
    public ModelCoverageEntity() {
    }

    // 全参构造器
    public ModelCoverageEntity(Long id, String name, int age, String param) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.param = param;
    }

    // getter/setter
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // param 属性 setter（不指定set param，条件覆盖补充 doSetTest：set invoke 会取请求参数列表的第1个数作为param，此处覆盖请求参数为空的场景）
    public void setParam() {
        System.out.println("do nothing with no param");
    }

    // param 属性 （覆盖方法名中包括param属性名称，但是非setter、getter定义）
    public void randomParam() {
        System.out.println("random param");
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }

    @Override
    public boolean equals(Object o) {
        /*
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelCoverageEntity that = (ModelCoverageEntity) o;
        return age == that.age && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(param, that.param);
        */
        return false;
    }

    // toString
    @Override
    public String toString() {
        return "PlainUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}