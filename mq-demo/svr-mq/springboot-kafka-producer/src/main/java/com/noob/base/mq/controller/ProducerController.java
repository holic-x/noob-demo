package com.noob.base.mq.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.noob.base.mq.model.dto.IncrCountReq;
import com.noob.base.mq.service.CountService;
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

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping("/kafka/producer")
@Slf4j
public class ProducerController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    CountService countService;

    // 引入MQ：此处生产者做发送消息（执行逻辑由消费者实现）
    @PostMapping(value = "/decouplingWithMQ",consumes = "application/json;charset=utf-8")
    public ResponseEntity<String> decouplingWithMQ(@RequestBody IncrCountReq incrCountReq) {
        // 传递消息，发送到kafka消息队列
        kafkaTemplate.send("tp-mq-decoupling", JSON.toJSONString(incrCountReq));
        return ResponseEntity.ok("success");
    }

    // 模拟消费者消费
    // @KafkaListener(topics = "tp-mq-decoupling",groupId = "TEST_GROUP",concurrency = "1",containerFactory = "kafkaManualAckListenerContainerFactory")
    @KafkaListener(topics = "tp-mq-decoupling",groupId = "TEST_GROUP") // 使用Springboot的KafkaListener注册一个kafka消费者
    // public void topicConsumer(ConsumerRecord<?,?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
    public void topicConsumer(ConsumerRecord<?,?> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional message = Optional.ofNullable(record.value());
        if(message.isPresent()){
            // 解析消息
            Object msg = message.get();
            log.info("接收到的kafka消息:{}",msg);
            try{
                // 模拟消费消息
                countService.incrManyTimes(JSONObject.parseObject(msg.toString(),IncrCountReq.class).getNum());
                log.info("kafka消息消费成功:topic:{} msg:{}",topic,msg);
            }catch (Exception e){
                e.printStackTrace();
                log.error("kafka消息消费失败:topic:{} msg:{}",topic,msg);
            }
        }
    }
}
