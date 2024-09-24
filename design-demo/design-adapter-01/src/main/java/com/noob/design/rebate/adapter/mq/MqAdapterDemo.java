package com.noob.design.rebate.adapter.mq;

import com.alibaba.fastjson2.JSON;
import com.noob.design.rebate.old.mq.CreateCountMq;
import com.noob.design.rebate.old.mq.InnerOrderMq;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * MQ 消息适配（将传递的字符串类型的MQ消息转换成统一的MQ消息体）
 */
public class MqAdapterDemo {

    public static void main(String[] args) {
        /**
         * 模拟开户信息适配
         */
        CreateCountMq createCountMq = new CreateCountMq("1001","浙江杭州",new Date(),"新开户");
        // 构建开户信息属性关联
        Map<String,String> createCountLink = new HashMap<>();
        createCountLink.put("userId","number");
        createCountLink.put("bizId","number");
        createCountLink.put("bizTime","accountDate");
        createCountLink.put("desc","desc");
        // 通过适配器进行转化
        RebateMqInfo rebateMqInfo1 = MqAdapter.filter(JSON.toJSONString(createCountMq),createCountLink);
        System.out.println("适配前：" + JSON.toJSONString(createCountMq));
        System.out.println("适配后：" + JSON.toJSONString(rebateMqInfo1));

        /**
         * 模拟订单信息适配
         */
        InnerOrderMq innerOrderMq = new InnerOrderMq("1001","o1001",new Date(),"电脑","acer 电脑",new BigDecimal("10000"));
        // 构建开户信息属性关联
        Map<String,String> innerOrderLink = new HashMap<>();
        innerOrderLink.put("userId","uid");
        innerOrderLink.put("bizId","orderId");
        innerOrderLink.put("bizTime","orderTime");
        innerOrderLink.put("params",JSON.toJSONString(innerOrderMq));
        // 通过适配器进行转化
        RebateMqInfo rebateMqInfo2 = MqAdapter.filter(JSON.toJSONString(innerOrderMq),innerOrderLink);
        System.out.println("适配前：" + JSON.toJSONString(innerOrderMq));
        System.out.println("适配后：" + JSON.toJSONString(rebateMqInfo2));
    }
}
