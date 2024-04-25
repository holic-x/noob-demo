package com.noob.demo.service;


import org.springframework.stereotype.Component;

@Component
public interface BaseService<T>{

    T getBean();

    //公共的增删查改
}
