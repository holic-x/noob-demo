package com.noob.design.rebate.adapter.mq;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * MQ 消息适配（将传递的字符串类型的MQ消息转换成统一的MQ消息体）
 */
public class MqAdapter {

    /**
     * 将传递的strJson映射到相应的字段中
     * @param strJson
     * @param link
     * @return
     */
    public static RebateMqInfo filter(String strJson, Map<String,String> link) {
        Map obj = JSON.parseObject(strJson);
        RebateMqInfo rebateMqInfo = new RebateMqInfo();
        link.keySet().forEach(key->{
            Object val = obj.get(link.get(key));
            try {
                // RebateMqInfo.class.getMethod("set" + key.substring(0,1).toUpperCase() + key.substring(1),String.class).invoke(rebateMqInfo,val);

                // 根据不同属性类型进行设定
                Field field = RebateMqInfo.class.getDeclaredField(key);
                String type = String.valueOf(field.getType()); // getDeclaredField 私有属性获取
                if(type.equals("class java.lang.String")){
                    RebateMqInfo.class.getMethod("set" + key.substring(0,1).toUpperCase() + key.substring(1),String.class).invoke(rebateMqInfo,val);
                }else if(type.equals("class java.util.Date")){
                    field.setAccessible(true);
                    field.set(rebateMqInfo,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(val.toString()));
                }else if(type.equals("interface java.util.Map")){
                    field.setAccessible(true);
                    field.set(rebateMqInfo, (Map<String,Object>)obj);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return rebateMqInfo;
    }
}
