package com.noob.design.rebate.old.mq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * MQ 消息体：开户MQ信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCountMq {
    private String number; // 开户编号
    private String address;// 开户地址
    private Date accountDate;// 开户时间
    private String desc;// 开户描述
}
