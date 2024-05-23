package com.noob.java8.stream.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamFindFirstExample {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();

        stringList.add("one");
        stringList.add("two");
        stringList.add("three");
        stringList.add("one");

        Stream<String> stream = stringList.stream();

        Optional<String> anyElement = stream.findFirst();
        if (anyElement.isPresent()) {
            System.out.println(anyElement.get());
        } else {
            System.out.println("not found");
        }
    }
}
