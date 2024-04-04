package com.noob.annotation.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @description:
 * @author：holic-x
 * @date: 2018/8/10
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class BaseUserProvider {

    public String selectById(Map<String,Object> params){
        return new SQL(){
            {
                SELECT("*");
                FROM("user");
                WHERE("id="+params.get("id"));
            }
        }.toString();
    }

}
