package com.noob.base.mq.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.noob.base.mq.model.dto.IncrCountReq;
import com.noob.base.mq.service.CountService;
import com.noob.base.mq.service.FlowService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/flow")
public class FlowController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private FlowService flowService;

    // 传统方式
    @GetMapping(value = "/peakClipping")
    public ResponseEntity<String> peakClipping() {
        // 模拟调用下游服务
        flowService.handler();
        return ResponseEntity.ok("success");
    }

    // 引入MQ：此处生产者做发送消息（执行逻辑由消费者实现）
    @GetMapping(value = "/peakClippingWithMQ")
    public ResponseEntity<String> peakClippingWithMQ() {
        String msg = "hello kafka";
        // 传递消息，发送到kafka消息队列
        kafkaTemplate.send("tp-mq-peakClipping", msg);
        return ResponseEntity.ok("success");
    }

    // 模拟消费者消费
    @KafkaListener(topics = "tp-mq-peakClipping",groupId = "TEST_GROUP") // 使用Springboot的KafkaListener注册一个kafka消费者
//     public void topicConsumer(ConsumerRecord<?,?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
    public void topicConsumer(ConsumerRecord<?,?> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional message = Optional.ofNullable(record.value());
        if(message.isPresent()){
            // 解析消息
            Object msg = message.get();
            log.info("接收到的kafka消息:{}",msg);
            try{
                // 模拟消费消息
                flowService.handler();
                // 模拟沉睡1s（假设消费者每秒只能处理1条消息，通过主动sleep模拟这种情况）
                TimeUnit.SECONDS.sleep(1);
                log.info("kafka消息消费成功:topic:{} msg:{}",topic,msg);
            }catch (Exception e){
                e.printStackTrace();
                log.error("kafka消息消费失败:topic:{} msg:{}",topic,msg);
            }
        }
    }

}
