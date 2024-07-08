package com.noob.base.batchHandle.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.noob.base.batchHandle.entity.model.TLimit;
import com.noob.base.batchHandle.entity.model.TUserBatch;
import com.noob.base.batchHandle.service.TLimitService;
import com.noob.base.batchHandle.mapper.TLimitMapper;
import com.noob.base.batchHandle.util.RandomGenEntityUtil;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
* @author hahabibu
* @description 针对表【t_limit】的数据库操作Service实现
* @createDate 2024-07-06 10:35:39
*/
@Service
public class TLimitServiceImpl extends ServiceImpl<TLimitMapper, TLimit>
    implements TLimitService{

    @Override
    public boolean batchInsert(List<TLimit> limits) {
        // java.lang.StackOverflowError: null   错用 this.batchInsert(limits)  嵌套太深，因为用错方法，误认为这是mybatis 的批量操作
         return this.saveBatch(limits);
    }

    @Override
    public boolean exportInsertSQL(List<TLimit> limits,String filePath) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 封装SQL文本信息
        List<String> insertList = new ArrayList<>();
        limits.forEach(entity -> {
            // 拼接SQL语句
            StringBuffer insertSql = new StringBuffer();
            // 主键自增
            /*
            insertSql.append("insert into t_limit (key1,key2,key3,key_part1,key_part2,key_part3,common_field) values( ")
                    .append("'" + entity.getKey1() + "',")
                    .append(entity.getKey2() + ",")
                    .append("'" + entity.getKey3() + "',")
                    .append("'" + entity.getKeyPart1() + "',")
                    .append("'" + entity.getKeyPart2() + "',")
                    .append("'" + entity.getKeyPart3() + "',")
                    .append("'" + entity.getCommonField() + "');");
             */
            insertSql.append("'" + entity.getKey1() + "',")
                    .append("'" + entity.getKey2() + "',")
                    .append("'" + entity.getKey3() + "',")
                    .append("'" + entity.getKeyPart1() + "',")
                    .append("'" + entity.getKeyPart2() + "',")
                    .append("'" + entity.getKeyPart3() + "',")
                    .append("'" + entity.getCommonField() + "'");
            insertList.add(insertSql.toString());
        });

        // 写入文件
        FileWriter writer = new FileWriter(new File(filePath));
        insertList.forEach(insertSql -> {
            try {
                writer.write(insertSql + "\n");
            } catch (IOException e) {
                System.out.println("数据写入异常" + insertSql);
                throw new RuntimeException(e);
            }
        });
        // 关闭写入流
        writer.close();
        return true;
    }
}




