package com.noob.framework.transaction.service;

import com.noob.framework.transaction.entity.TableEntity;
import com.noob.framework.transaction.mapper.TableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class TransactionServiceD {

    @Autowired
    private TableMapper tableMapper;

    @Transactional
    public void add(){
        try{
            tableMapper.insertTableA(new TableEntity(UUID.randomUUID().toString().replaceAll("-","")));
            tableMapper.insertTableB(new TableEntity(UUID.randomUUID().toString().replaceAll("-","")));
            throw new RuntimeException();
        }catch (Exception e){
            System.out.println("捕获异常");
        }
    }
}
