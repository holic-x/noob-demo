package com.noob.framework.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PayServiceTest {

    @Autowired
    private PayService payService;

    @Test
    void minGoodsnum() throws Exception {

        int store = payService.minGoodsnum(-1);
        System.out.println("当前库存为：" + store);

    }
}