package com.noob.redis.aopLock.model;

import lombok.Data;

@Data
public class Resp<T> {
    private Integer code;
    private String message;
    private T data;
    private final static Integer success=0;
    private final static Integer fail=-1;

    public static Resp buildSuccess(){
        Resp resp = new Resp();
        resp.setCode(success);
        return resp;
    }

    public static <T> Resp<T> buildSuccess(String message){
        Resp resp = new Resp();
        resp.setCode(success);
        resp.setMessage(message);
        return resp;
    }
    public static <T> Resp<T> buildDataSuccess(T data){
        Resp resp = new Resp();
        resp.setCode(success);
        resp.setData(data);
        return resp;
    }

    public static Resp buildFail(String message){
        Resp resp = new Resp();
        resp.setCode(fail);
        resp.setMessage(message);
        return resp;
    }
    public static Resp buildFail(Integer code,String message){
        Resp resp = new Resp();
        resp.setCode(code);
        resp.setMessage(message);
        return resp;
    }
}
