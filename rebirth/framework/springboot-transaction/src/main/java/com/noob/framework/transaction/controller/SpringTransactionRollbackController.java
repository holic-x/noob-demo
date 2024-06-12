package com.noob.framework.transaction.controller;

import com.noob.framework.transaction.service.TransactionServiceD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/api")
public class SpringTransactionRollbackController {
    @Autowired
    private TransactionServiceD transactionServiceD;

    @RequestMapping("/spring-transaction/rollback")
    public String testTransaction() throws SQLException {
        transactionServiceD.add();
        return "SUCCESS";
    }
}
