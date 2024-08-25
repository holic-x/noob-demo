package com.noob.base.mq.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class DispatchConsumer {
    // ------------------ 模拟消费者消费(此时消费者应对应不同的service处理消息) ------------------------
    // 使用Springboot的KafkaListener注册一个kafka消费者
    @KafkaListener(topics = "tp-mq-dispatch",groupId = "TEST_GROUP1",concurrency = "1",containerFactory = "myKafkaContainerFactory")
    public void topicConsumerB(ConsumerRecord<?,?> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional message = Optional.ofNullable(record.value());
        if(message.isPresent()){
            // 解析消息
            Object msg = message.get();
            log.info("接收到的kafka消息:{}",msg);
            try{
                // 模拟消费消息
                System.out.println("SvrB received msg：" + msg);
                log.info("kafka消息消费成功:topic:{} msg:{}",topic,msg);
            }catch (Exception e){
                e.printStackTrace();
                log.error("kafka消息消费失败:topic:{} msg:{}",topic,msg);
            }
        }
    }

    // 使用Springboot的KafkaListener注册一个kafka消费者
    @KafkaListener(topics = "tp-mq-dispatch",groupId = "TEST_GROUP2",concurrency = "1",containerFactory = "myKafkaContainerFactory")
    public void topicConsumerC(ConsumerRecord<?,?> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional message = Optional.ofNullable(record.value());
        if(message.isPresent()){
            // 解析消息
            Object msg = message.get();
            log.info("接收到的kafka消息:{}",msg);
            try{
                // 模拟消费消息
                System.out.println("SvrC received msg：" + msg);
                log.info("kafka消息消费成功:topic:{} msg:{}",topic,msg);
            }catch (Exception e){
                e.printStackTrace();
                log.error("kafka消息消费失败:topic:{} msg:{}",topic,msg);
            }
        }
    }

    // 使用Springboot的KafkaListener注册一个kafka消费者
    @KafkaListener(topics = "tp-mq-dispatch",groupId = "TEST_GROUP3",concurrency = "1",containerFactory = "myKafkaContainerFactory")
    public void topicConsumerD(ConsumerRecord<?,?> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional message = Optional.ofNullable(record.value());
        if(message.isPresent()){
            // 解析消息
            Object msg = message.get();
            log.info("接收到的kafka消息:{}",msg);
            try{
                // 模拟消费消息
                System.out.println("SvrD received msg：" + msg);
                log.info("kafka消息消费成功:topic:{} msg:{}",topic,msg);
            }catch (Exception e){
                e.printStackTrace();
                log.error("kafka消息消费失败:topic:{} msg:{}",topic,msg);
            }
        }
    }
}
