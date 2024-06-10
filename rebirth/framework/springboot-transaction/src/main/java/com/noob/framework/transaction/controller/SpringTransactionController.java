package com.noob.framework.transaction.controller;

import com.noob.framework.transaction.service.TransactionServiceA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SpringTransactionController {

    @Autowired
    private TransactionServiceA transactionServiceA;

    @RequestMapping("/spring-transaction")
    public String testTransaction() {
        // 调用serviceA执行方法
        transactionServiceA.methodA();
        return "SUCCESS";
    }
}
