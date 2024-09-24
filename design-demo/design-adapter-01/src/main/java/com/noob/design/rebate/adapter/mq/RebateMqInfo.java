package com.noob.design.rebate.adapter.mq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;


/**
 * 统一MQ消息体定义
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RebateMqInfo {
    private String userId; // 用户ID
    private String bizId; // 业务ID
    private Date bizTime; // 业务时间
    private String desc; // 业务描述

    // 可设定Map额外接收业务相关扩展参数,通过反射机制动态生成并映射
     private Map<String,Object> params;

}
