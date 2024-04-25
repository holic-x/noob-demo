package com.noob.demo.observer.carlottery.design.event.listener;

import com.noob.demo.observer.carlottery.common.LotteryResult;

public interface EventListener {

    void doEvent(LotteryResult result);

}
