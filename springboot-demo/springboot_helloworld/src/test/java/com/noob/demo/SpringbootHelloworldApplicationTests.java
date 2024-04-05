package com.noob.demo;

import com.noob.demo.entity.Result;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class SpringbootHelloworldApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    void callByRestTemplate() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        // 基于restTemplate 调用删除
        ResponseEntity<Result> resultResponseEntity = restTemplate.exchange("http://localhost:8080/user/{id}", HttpMethod.DELETE, null, Result.class, 1);
        System.out.println(resultResponseEntity.toString());
    }

}
