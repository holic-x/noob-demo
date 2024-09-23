package com.noob.design.adapter.mq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * MQ 消息体：内部订单MQ信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InnerOrderMq {
    private String uid; // 用户ID
    private String orderId; // 订单ID
    private Date orderTime;// 下单时间
    private String sku; // 商品信息
    private String skuName; // 商品名称
    private BigDecimal decimal;// 订单金额
}
