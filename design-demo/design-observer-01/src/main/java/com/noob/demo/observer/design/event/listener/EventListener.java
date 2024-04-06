package com.noob.demo.observer.design.event.listener;

import com.noob.demo.observer.common.LotteryResult;

public interface EventListener {

    void doEvent(LotteryResult result);

}
