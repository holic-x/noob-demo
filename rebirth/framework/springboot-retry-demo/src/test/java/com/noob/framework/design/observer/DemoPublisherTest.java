package com.noob.framework.design.observer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DemoPublisherTest {

    @Autowired
    private DemoPublisher demoPublisher;

    @Test
    void publish() {
        demoPublisher.publish("hello world");
    }
}