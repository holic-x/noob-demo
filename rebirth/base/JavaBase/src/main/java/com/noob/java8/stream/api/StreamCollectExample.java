package com.noob.java8.stream.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamCollectExample {
    public static void main(String[] args) {

        List<String> stringList = new ArrayList<>();

        stringList.add("One flew over the cuckoo's nest");
        stringList.add("To kill a muckingbird");
        stringList.add("Gone with the wind");

        Stream<String> stream = stringList.stream();

        List<String> stringsAsUppercaseList = stream
                .map(value -> value.toUpperCase())
                .collect(Collectors.toList());

        System.out.println(stringsAsUppercaseList);

    }
}
