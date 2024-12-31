package com.noob.rpc.spi.impl;

import com.noob.rpc.spi.MyLog;

public class WebLog implements MyLog {
    @Override
    public void info(String str) {
        System.out.println("web log out:" + str);
    }
}
