package com.noob.base.demo.interceptor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @PostMapping("/pay")
    @Idempotent(key = "orderId") // 使用幂等性注解
    public String pay(@RequestHeader String orderId) {
        // 支付逻辑
        return "支付成功";
    }
}