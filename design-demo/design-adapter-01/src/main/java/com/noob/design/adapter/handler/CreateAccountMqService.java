package com.noob.design.adapter.handler;

import com.alibaba.fastjson2.JSON;
import com.noob.design.adapter.mq.CreateCountMq;

/**
 * MQ 消息处理服务类：CreateAccount
 */
public class CreateAccountMqService {

    public void onMessage(String message) {
        // 解析MQ消息，然后调用服务进行处理
        CreateCountMq createCountMq = JSON.parseObject(message,CreateCountMq.class);
        // 业务逻辑处理
        System.out.println("开户处理" + createCountMq.toString());
    }

}
