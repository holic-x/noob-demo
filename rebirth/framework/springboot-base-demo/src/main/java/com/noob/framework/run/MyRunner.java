package com.noob.framework.run;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements ApplicationRunner,CommandLineRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("hello ApplicationRunner");
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("hello CommandLineRunner");
    }
}
