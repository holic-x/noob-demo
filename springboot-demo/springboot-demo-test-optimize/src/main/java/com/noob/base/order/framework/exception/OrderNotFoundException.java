package com.noob.base.order.framework.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) { super(message); }
}