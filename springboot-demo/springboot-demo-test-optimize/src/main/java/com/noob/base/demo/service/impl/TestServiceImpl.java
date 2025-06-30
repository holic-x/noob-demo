package com.noob.base.demo.service.impl;

import com.noob.base.demo.service.DataService;
import com.noob.base.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("testService")
public class TestServiceImpl implements TestService {

    @Autowired
    private DataService dataService;

    @Override
    public void test01() {
        try {
            dataService.addData();
        }catch (Exception e){
            System.out.println("添加数据失败");
            System.out.println(e);
        }
    }

    @Override
    public void test02() {
        try {
            dataService.addData();
        }catch (Throwable e){
            System.out.println("添加数据失败");
            System.out.println(e);
        }
    }
}
