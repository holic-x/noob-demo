package com.noob.design.pay;

import java.math.BigDecimal;

/**
 * 支付处理器
 * 核心：根据不同的支付渠道、验证模式进行组合，构建支付场景
 * 支付渠道 channelType：1-wechatPay、2-aliPay
 * 验证模式 modeType：1-密码验证、2-指纹验证、3-人脸识别
 */
public class PayController {
    // 模拟支付
    public boolean pay(String uid, String traceId, BigDecimal money,int channelType,int modeType){
        String payInfo = uid + "," + traceId + "," + money + "," + channelType + "," + modeType;
        // 根据不同的支付渠道、验证模式进行组合，构建支付场景
        if(1==channelType){
            // 微信支付处理
            System.out.println("wechat pay");
            if(1==modeType){
                System.out.println("wechat 密码验证");
            }else if(2==modeType){
                System.out.println("wechat 指纹验证");
            }else if(3==modeType){
                System.out.println("wechat 人脸识别");
            }
            System.out.println("wechat 支付成功：" + payInfo);
        }else if(2==channelType){
            // 支付宝支付处理
            System.out.println("ali pay");
            if(1==modeType){
                System.out.println("ali 密码验证");
            }else if(2==modeType){
                System.out.println("ali 指纹验证");
            }else if(3==modeType){
                System.out.println("ali 人脸识别");
            }
            System.out.println("ali 支付成功：" + payInfo);
        }
        return true;
    }
}
