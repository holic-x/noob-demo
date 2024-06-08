package com.noob.framework.transaction.service;

import com.noob.framework.transaction.entity.TableEntity;
import com.noob.framework.transaction.mapper.TableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class TransactionServiceB {

    @Autowired
    private TableMapper tableMapper;

    @Transactional
    public void methodB(){
        System.out.println("methodB 执行 插入数据");
        tableMapper.insertTableB(new TableEntity(UUID.randomUUID().toString().replaceAll("-","")));
        // 模拟业务处理异常
        throw new RuntimeException();
    }
}
