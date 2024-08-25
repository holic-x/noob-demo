package com.noob.base.mq.controller;

import com.noob.base.mq.service.FlowService;
import com.noob.base.mq.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/dispatch")
public class DispatchController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private NoticeService noticeService;

    // 传统方式
    @GetMapping(value = "/notice")
    public ResponseEntity<String> notice() {
        String msg = "Hello kafka";
        // 依次通知各个服务节点
        noticeService.notice("svrB",msg);
        noticeService.notice("svrC",msg);
        noticeService.notice("svrD",msg);
        // ..... 更多节点接入
        return ResponseEntity.ok("success");
    }

    // 引入MQ：此处生产者做发送消息（执行逻辑由消费者实现）
    @GetMapping(value = "/noticeWithMQ")
    public ResponseEntity<String> noticeWithMQ() {
        String msg = "hello kafka";
        // 传递消息，发送到kafka消息队列
        kafkaTemplate.send("tp-mq-dispatch", msg);
        return ResponseEntity.ok("success");
    }

}
