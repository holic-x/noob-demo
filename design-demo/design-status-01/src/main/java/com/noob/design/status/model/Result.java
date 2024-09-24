package com.noob.design.status.model;

public enum Result {

    SUCCESS(1,"操作成功"),
    FAIL(-1,"操作失败"),
    ILLEGAL(1001,"操作非法"),

    ;

    private int code;
    private String msg;
    private Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
