package com.noob.java8.stream.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StreamNoneExample {

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();

        stringList.add("abc");
        stringList.add("def");

        Stream<String> stream = stringList.stream();

        boolean noneMatch = stream.noneMatch((element) -> {
            return "xyz".equals(element);
        });

        System.out.println("noneMatch = " + noneMatch); //输出 noneMatch = true

    }

}
