package com.noob.base.runtimeFailures.deadThread;

import lombok.Getter;
import lombok.Setter;

// 账户类
@Setter
@Getter
class Account {
    private String accountId; // 账户ID
    private int balance; // 余额

    public Account(String accountId, int balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    // 增加余额
    public void increase(int amount) {
        balance += amount;
    }

    // 减少余额
    public void decrease(int amount) {
        balance -= amount;
    }
}