package com.noob.rpc.simple.protocol;

import lombok.Data;

import java.io.Serializable;

@Data
public class Response implements Serializable {

    private int code = 0; // 响应的错误码，正常响应为0，非0表示异常响应

    private String errMsg; // 异常信息

    private Object result; // 响应结果

}
