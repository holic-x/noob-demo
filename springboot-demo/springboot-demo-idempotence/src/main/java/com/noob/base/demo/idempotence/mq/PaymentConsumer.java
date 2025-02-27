package com.noob.base.demo.idempotence.mq;


import com.noob.base.demo.model.entity.Message;

import java.util.HashSet;
import java.util.Set;

/**
 * ④ 基于【消息队列MQ】实现幂等性（worker消费方进行校验）
 */
public class PaymentConsumer {
    private Set<String> processedMessageIds = new HashSet<>();

    public void handleMessage(Message message) {
        String messageId = message.getMessageId();
        if (processedMessageIds.contains(messageId)) {
            return; // 消息已处理
        }
        processPayment(message); // 处理支付
        processedMessageIds.add(messageId); // 记录消息ID
    }

    // 模拟处理支付
    private void processPayment(Message message) {
        // ... 处理支付 ...
    }
}