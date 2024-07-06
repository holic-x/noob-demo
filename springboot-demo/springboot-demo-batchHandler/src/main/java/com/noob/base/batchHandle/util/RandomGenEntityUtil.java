package com.noob.base.batchHandle.util;

import cn.hutool.core.util.RandomUtil;
import com.noob.base.batchHandle.entity.model.SingleTable;
import com.noob.base.batchHandle.entity.model.TLimit;
import com.noob.base.batchHandle.entity.model.TUserBatch;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 随机生成实体工具类
 */
public class RandomGenEntityUtil {

    public static List<SingleTable> genSingleTable(String tableName){
        List<SingleTable> tables = new ArrayList<SingleTable>();



        return tables;
    }

    public static List<TLimit> genTLimit(int num){
        // 分批进行处理（例如每次插入1w条数据）
        List<TLimit> list = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            TLimit tLimit = new TLimit();                       
            tLimit.setKey1(RandomGenUtil.genUuid(6));
             tLimit.setKey2(i);
//            tLimit.setKey2(RandomGenUtil.genNum(6)); error
            tLimit.setKey3(RandomGenUtil.genUuid(6));
            tLimit.setKeyPart1(RandomGenUtil.genUuid(10));
            tLimit.setKeyPart2(RandomGenUtil.genUuid(6));
            tLimit.setKeyPart3(RandomGenUtil.genUuid(10));
            tLimit.setCommonField(RandomGenUtil.genUuid(10));
            list.add(tLimit);     
          }
        return list;
    }


    public static List<TUserBatch> genTUserBatch(int num){
        // long time = System.currentTimeMillis();  时间戳（如果通过int接收插入数据时会超出范围限制）
        // long time = new Date().getTime();
        Date time = new Date();
        // 分批进行处理（例如每次插入1w条数据）
        List<TUserBatch> list = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            TUserBatch tUserBatch = new TUserBatch();
            // 账号：邮箱（随机字符串 + 域名）
            tUserBatch.setAccount(RandomGenUtil.genEmail("163.com"));
            tUserBatch.setMobile(RandomGenUtil.genMobile());
            tUserBatch.setAdd_time(time);
            list.add(tUserBatch);
        }
        return list;
    }

}
