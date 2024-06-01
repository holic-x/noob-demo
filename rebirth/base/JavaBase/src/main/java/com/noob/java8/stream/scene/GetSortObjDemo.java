package com.noob.java8.stream.scene;


import java.util.Arrays;
import java.util.List;

class User {
    private Long userId;
    private String name;
    private Integer age;
    private Double salary;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User(Long userId, String name, Integer age, Double salary) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.salary = salary;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}

/**
 * 场景2：Stream中排序对象集合
 */
public class GetSortObjDemo {

    public static void main(String[] args) {
        List<User> userList = Arrays.asList(new User(1000L, "First", 25, 30000D),
                new User(2000L, "Second", 30, 45000D),
                new User(3000L, "Third", 35, 25000D));

        // 使用字段包装类型的 compareTo 进行排序, 这个方式可以简化成下面的使用 Comparator.comparing 的例子
        userList.stream().sorted(
                        (p1, p2) ->
                                (p1.getUserId().compareTo(p2.getUserId()))
                )
                .forEach(user -> System.out.println(user.getName()));

    }

}
