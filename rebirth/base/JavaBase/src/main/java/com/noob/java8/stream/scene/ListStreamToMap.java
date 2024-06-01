package com.noob.java8.stream.scene;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class Animal {
    private int id;
    private String name;

    public Animal(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

/**
 * 场景4：把对象 List 转换为对象 Map
 */
public class ListStreamToMap {
    public static void main(String[] args) {
        List<Animal> aList = new ArrayList<>();
        aList.add(new Animal(1, "Elephant"));
        aList.add(new Animal(2, "Bear"));

        Map<Integer, Animal> map = aList.stream()
                .collect(Collectors.toMap(Animal::getId, Function.identity()));

        map.forEach((integer, animal) -> {
            System.out.println(animal.getName());
        });

    }
}
