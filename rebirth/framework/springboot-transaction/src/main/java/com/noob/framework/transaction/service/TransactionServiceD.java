package com.noob.framework.transaction.service;

import com.noob.framework.transaction.entity.TableEntity;
import com.noob.framework.transaction.exception.BusinessException;
import com.noob.framework.transaction.mapper.TableMapper;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.UUID;

@Service
public class TransactionServiceD {

    @Autowired
    private TableMapper tableMapper;

//    @Transactional(rollbackFor = Exception.class)
    public void add() throws SqlSessionException {
        tableMapper.insertTableA(new TableEntity(UUID.randomUUID().toString().replaceAll("-", "")));
        tableMapper.insertTableB(new TableEntity(UUID.randomUUID().toString().replaceAll("-", "")));
        throw new SqlSessionException();
    }
}
