package com.noob.rpc.spi.impl;

import com.noob.rpc.spi.MyLog;

public class ConsoleLog implements MyLog {
    @Override
    public void info(String str) {
        System.out.println("console log out:" + str);
    }
}
