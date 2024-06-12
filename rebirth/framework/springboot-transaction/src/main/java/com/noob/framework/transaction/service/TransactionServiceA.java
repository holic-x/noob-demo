package com.noob.framework.transaction.service;

import com.noob.framework.transaction.entity.TableEntity;
import com.noob.framework.transaction.mapper.TableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class TransactionServiceA {

    @Autowired
    private TableMapper tableMapper;

    @Autowired
    private TransactionServiceB transactionServiceB;

    @Transactional
    public void methodA(){
        tableMapper.insertTableA(new TableEntity(UUID.randomUUID().toString().replaceAll("-","")));
        transactionServiceB.methodB();
    }
}
