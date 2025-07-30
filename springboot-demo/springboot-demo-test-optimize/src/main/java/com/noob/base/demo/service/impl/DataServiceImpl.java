package com.noob.base.demo.service.impl;


import com.noob.base.demo.service.DataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("dataService")
public class DataServiceImpl implements DataService {

    // @Transactional
    @Transactional(rollbackFor=Exception.class)
    @Override
    public void addData() {
        System.out.println("执行ing");
        // mock 执行异常，抛出自定义异常概念
        throw new RuntimeException("执行addData失败");
    }
}
