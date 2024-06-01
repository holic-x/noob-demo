package com.noob.java8.stream.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamMinExample {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();

        stringList.add("abc");
        stringList.add("def");
        Stream<String> stream = stringList.stream();

        // 作为 min 方法参数的Lambda 表达式可以简写成 String::compareTo
        // Optional<String> min = stream.min(String::compareTo);
        Optional<String> min = stream.min((val1, val2) -> {
            return val1.compareTo(val2);
        });

        String minString = min.get();

        System.out.println(minString); // abc
    }
}
