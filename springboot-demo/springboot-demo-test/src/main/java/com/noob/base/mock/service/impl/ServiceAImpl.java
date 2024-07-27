package com.noob.base.mock.service.impl;

import com.noob.base.mock.service.ServiceA;
import com.noob.base.mock.service.ServiceB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceAImpl implements ServiceA {

    @Autowired
    private ServiceB serviceB;

    @Override
    public String methodA() {
        System.out.println("methodA");
        int res = serviceB.methodB();
        System.out.println("call ServiceB methodB:" + res);
        if(res==888){
            return "success";
        }else {
            return "fail";
        }
    }
}
