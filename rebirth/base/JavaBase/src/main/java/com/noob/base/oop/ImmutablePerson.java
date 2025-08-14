package com.noob.base.oop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

// 1. 类声明为 final（防止被继承）
public final class ImmutablePerson {
    // 2. 所有字段私有 + final
    private final String name;
    private final int age;
    private final List<String> hobbies;  // 可变对象成员

    // 3. 构造器初始化所有字段（深度拷贝可变对象）
    public ImmutablePerson(String name, int age, List<String> hobbies) {
        this.name = Objects.requireNonNull(name, "Name cannot be null");
        this.age = age;
        // 防御性拷贝（deep copy）
        this.hobbies = Collections.unmodifiableList(new ArrayList<>(hobbies));
    }

    // 4. 不提供 setter 方法

    // 5. Getter 方法返回不可修改的副本
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<String> getHobbies() {
        // 返回不可修改的副本
        return Collections.unmodifiableList(new ArrayList<>(hobbies));
    }

    // 6. 修改状态的方法返回新对象
    public ImmutablePerson withAge(int newAge) {
        return new ImmutablePerson(this.name, newAge, new ArrayList<>(this.hobbies));
    }
}