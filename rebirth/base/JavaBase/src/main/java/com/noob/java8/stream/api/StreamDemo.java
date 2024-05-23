package com.noob.java8.stream.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream API操作 demo
 */
public class StreamDemo {

    public void test(){
        List<String> stringList = new ArrayList<>();
        stringList.add("ONE");
        stringList.add("TWO");
        stringList.add("THREE");
        // 获取流
        Stream<String> stream = stringList.stream();
        // 流处理
        long count = stream
                .map((value) -> value.toLowerCase())
                .count();

        System.out.println("count = " + count);
    }

    public static void main(String[] args) {

        List<String> stringList = new ArrayList<String>();

        stringList.add("One flew over the cuckoo's nest");
        stringList.add("To kill a muckingbird");
        stringList.add("Gone with the wind");

        Stream<String> stream = stringList.stream();

        stream.flatMap((value) -> {
            String[] split = value.split(" ");
            return Arrays.asList(split).stream();
        }).forEach((value) -> System.out.println(value));

    }
}


