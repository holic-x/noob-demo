package com.noob.demo.observer.carlottery.design.event.service;

import com.noob.demo.observer.carlottery.common.LotteryResult;
import com.noob.demo.observer.carlottery.common.MinibusTargetService;

import java.util.Date;

public class LotteryServiceImpl extends LotteryService {

    private MinibusTargetService minibusTargetService = new MinibusTargetService();

    @Override
    protected LotteryResult doDraw(String uId) {
        // 摇号
        String lottery = minibusTargetService.lottery(uId);
        // 结果
        return new LotteryResult(uId, lottery, new Date());
    }

}
