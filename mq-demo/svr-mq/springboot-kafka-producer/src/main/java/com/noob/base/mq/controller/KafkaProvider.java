package com.noob.base.mq.controller;

import com.alibaba.fastjson.JSONObject;
import com.noob.base.mq.model.dto.IncrCountReq;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Properties;


@Component
@Slf4j
public class KafkaProvider {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    // 发送模式测试
    public void send() {
        // 定义消息
        String msg = "hello kafka";

        // 方式1：同步模式
        try{
            kafkaTemplate.send("tp-test",msg);
        }catch (Exception e){
            e.printStackTrace();
        }

        // 方式2：发送即忘
        kafkaTemplate.send("tp-test",msg);

        // 方式3：异步模式
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("tp-test",msg);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("send success msg:{},with offset:{}", msg, result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("send fail msg:{},with offset:{}", msg, ex.getMessage());
            }
        });
    }


    // 写入策略配置
    public void writeStrategy(){
        // 配置生产者属性
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // 设置acks参数
        props.put(ProducerConfig.ACKS_CONFIG,"all");// 设置为"0"，"1"或 "all"
        // 创建Kafka生产者
        KafkaProducer<String,String> producer = new KafkaProducer<>(props);
    }

}
