package com.noob.base.batchHandle.util;

import com.noob.base.batchHandle.entity.model.SingleTable;
import com.noob.base.batchHandle.entity.model.TLimit;

import java.util.ArrayList;
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
            tLimit.setKeyPart1(RandomGenUtil.genUuid(6));
            tLimit.setKeyPart1(RandomGenUtil.genUuid(6));
            tLimit.setKeyPart1(RandomGenUtil.genUuid(6));
            tLimit.setCommonField(RandomGenUtil.genUuid(6));
            list.add(tLimit);     
          }
        return list;
    }

}
