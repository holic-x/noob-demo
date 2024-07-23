package com.noob.base.mock.service.impl;

import com.noob.base.mock.service.ServiceB;
import org.springframework.stereotype.Service;

@Service
public class ServiceBImpl implements ServiceB {
    @Override
    public int methodB() {
        System.out.println("methodB");
        return 1;
    }
}
