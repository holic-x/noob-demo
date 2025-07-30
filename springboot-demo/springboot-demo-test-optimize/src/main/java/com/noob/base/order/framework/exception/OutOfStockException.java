package com.noob.base.order.framework.exception;

// 自定义异常
public class OutOfStockException extends RuntimeException {
    public OutOfStockException(String message) { super(message); }
}