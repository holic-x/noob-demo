package com.noob.framework.transaction.exception;

public class BusinessException extends RuntimeException{
    public BusinessException() {
        System.out.println("自定义异常");
    }
}
