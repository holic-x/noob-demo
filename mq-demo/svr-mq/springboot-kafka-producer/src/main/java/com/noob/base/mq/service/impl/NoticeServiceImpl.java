package com.noob.base.mq.service.impl;

import com.noob.base.mq.service.NoticeService;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Override
    public void notice(String svr, String msg) {
        System.out.println(" 发送消息 msg:" + msg + " 通知：" + svr);
        switch (svr){
            case "svrB":
                handleBySvrB(msg);
                break;
            case "svrC":
                handleBySvrC(msg);
                break;
            case "svrD":
                handleBySvrD(msg);
                break;
        }
    }

    // 模拟服务B、C、D模块
    private void handleBySvrB(String msg){
        System.out.println("SvrB received msg：" + msg);
    }
    private void handleBySvrC(String msg){
        System.out.println("SvrB received msg：" + msg);
    }
    private void handleBySvrD(String msg){
        System.out.println("SvrB received msg：" + msg);
    }
}
