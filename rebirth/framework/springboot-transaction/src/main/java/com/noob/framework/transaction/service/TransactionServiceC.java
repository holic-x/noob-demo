package com.noob.framework.transaction.service;

import com.noob.framework.transaction.entity.TableEntity;
import com.noob.framework.transaction.mapper.TableMapper;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class TransactionServiceC {

    @Autowired
    private TableMapper tableMapper;

    /*
    @Lazy
    @Autowired
    private TransactionServiceC transactionServiceC;
     */

    @Lazy
    @Autowired
    private MyService myService;

    // 内部方法
    public void methodC(){
        tableMapper.insertTableA(new TableEntity(UUID.randomUUID().toString().replaceAll("-","")));
        myService.add();
        // add();
        // transactionServiceC.add();
        // ((TransactionServiceC) AopContext.currentProxy()).add();
    }

    @Transactional
    public void add(){
        tableMapper.insertTableB(new TableEntity(UUID.randomUUID().toString().replaceAll("-","")));
        throw new RuntimeException();
    }

    @Service
    public class MyService {
        @Transactional(rollbackFor=Exception.class)
        public void add(){
            tableMapper.insertTableB(new TableEntity(UUID.randomUUID().toString().replaceAll("-","")));
            throw new RuntimeException();
        }
    }

}
