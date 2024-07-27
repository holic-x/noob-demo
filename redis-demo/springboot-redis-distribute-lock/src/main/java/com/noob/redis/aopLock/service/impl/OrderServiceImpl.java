package com.noob.redis.aopLock.service.impl;

import com.noob.redis.aopLock.annotation.DistributionLock;
import com.noob.redis.aopLock.annotation.DistributionLockParam;
import com.noob.redis.aopLock.model.Resp;
import com.noob.redis.aopLock.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceImpl implements IOrderService {

    @DistributionLock(key = "updateOrderStatus",tryLockTime = 1000)
    @Override
    public Resp updateOrder(@DistributionLockParam String orderCode, Integer userId, Integer status){
        try {
            log.info("updateOrder 处理业务 start");
            // 模拟处理业务3s
            Thread.sleep(1000*3);
            log.info("updateOrder 处理业务 end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Resp.buildSuccess("修改订单状态成功");
    }

}

