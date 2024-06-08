package com.noob.framework.transaction.controller;

import com.noob.framework.transaction.service.TransactionServiceA;
import com.noob.framework.transaction.service.TransactionServiceC;
import org.omg.IOP.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SpringTransactionFailController {

    @Autowired
    private TransactionServiceC transactionServiceC;

    @RequestMapping("/spring-transaction/fail")
    public String testTransaction() {
        // 调用serviceA执行方法
        transactionServiceC.methodC();
        return "SUCCESS";
    }
}
