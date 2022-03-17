package com.sz.demo.service.impl;

import com.sz.demo.service.CarService;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
    @Override
    public void driveCar(String content) {
        System.out.println("开车上路ing:"+content);
    }
}
