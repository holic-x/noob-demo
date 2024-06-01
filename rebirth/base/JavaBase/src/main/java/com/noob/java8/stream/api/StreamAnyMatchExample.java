package com.noob.java8.stream.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StreamAnyMatchExample {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<String>();
        stringList.add("One flew over the cuckoo's nest");
        stringList.add("To kill a muckingbird");
        stringList.add("Gone with the wind");

        Stream<String> stream = stringList.stream();

        boolean anyMatch = stream.anyMatch((value) -> value.startsWith("One"));
        System.out.println(anyMatch);

    }
}
