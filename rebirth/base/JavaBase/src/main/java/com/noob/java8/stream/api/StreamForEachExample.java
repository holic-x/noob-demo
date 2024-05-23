package com.noob.java8.stream.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StreamForEachExample {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<String>();

        stringList.add("one");
        stringList.add("two");
        stringList.add("three");

        Stream<String> stream = stringList.stream();

        stream.forEach(System.out::println);
    }
}
