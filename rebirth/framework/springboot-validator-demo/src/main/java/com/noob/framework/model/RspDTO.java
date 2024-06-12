package com.noob.framework.model;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.Serializable;

// 返回类型定义
@Data
public class RspDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;
    private String msg;
    private Object data;
    public RspDTO(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public RspDTO(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
