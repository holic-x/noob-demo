package com.noob.java8.stream.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamReduceExample {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();

        stringList.add("One flew over the cuckoo's nest");
        stringList.add("To kill a muckingbird");
        stringList.add("Gone with the wind");

        Stream<String> stream = stringList.stream();

        Optional<String> reduced = stream.reduce((value, combinedValue) -> combinedValue + " + " + value);
        // 写程序的时候记得别忘了 reduced.ifPresent() 检查结果里是否有值
        System.out.println(reduced.get());
    }
}
