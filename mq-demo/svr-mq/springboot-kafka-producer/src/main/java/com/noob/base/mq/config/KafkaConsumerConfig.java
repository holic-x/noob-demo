package com.noob.base.mq.config;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.List;

/**
 * 手动自定义 kafka 消费者 ContainerFactory 配置demo
 */
@Configuration
//@EnableConfigurationProperties(KafkaProperties.class)
public class KafkaConsumerConfig {
 
    @Autowired
    private KafkaProperties properties;
 
//    @Value("${监听服务地址}")
//    private List<String> myServers;

    // @Bean("kafkaManualAckListenerContainerFactory")
    @Bean("myKafkaContainerFactory")
//    @ConditionalOnBean(ConcurrentKafkaListenerContainerFactoryConfigurer.class)
    public ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer) {
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, consumerFactory());
        return factory;
    }
 
    //获得创建消费者工厂
    public ConsumerFactory<Object, Object> consumerFactory() {
        KafkaProperties myKafkaProperties = JSON.parseObject(JSON.toJSONString(this.properties), KafkaProperties.class);
        //对模板 properties 进行定制化
        //....
        //例如：定制servers
//        myKafkaProperties.setBootstrapServers(myServers);
        return new DefaultKafkaConsumerFactory<>(myKafkaProperties.buildConsumerProperties());
    }
}