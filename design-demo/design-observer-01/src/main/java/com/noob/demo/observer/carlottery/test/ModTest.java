package com.noob.demo.observer.carlottery.test;

import com.alibaba.fastjson.JSON;
import com.noob.demo.observer.carlottery.common.LotteryResult;
import com.noob.demo.observer.carlottery.common.LotteryService;
import com.noob.demo.observer.carlottery.common.LotteryServiceImpl;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModTest {

    private Logger logger = LoggerFactory.getLogger(ModTest.class);

    @Test
    public void testCommon() {
        LotteryService lotteryService = new LotteryServiceImpl();
        LotteryResult result = lotteryService.doDraw("2765789109876");
        logger.info("测试结果：{}", JSON.toJSONString(result));
    }


    @Test
    public void testDesign() {
        com.noob.demo.observer.carlottery.design.event.service.LotteryService lotteryService = new com.noob.demo.observer.carlottery.design.event.service.LotteryServiceImpl();
        LotteryResult result = lotteryService.draw("2765789109876");
        logger.info("测试结果：{}", JSON.toJSONString(result));
    }
}
