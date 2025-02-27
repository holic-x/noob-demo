package com.noob.base.demo.model.entity;


import lombok.Data;

// 定义消息体
@Data
public class Message {
    String messageId; // 消息ID
    String content; // 消息内容
}
