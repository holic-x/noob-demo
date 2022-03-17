package com.sz.controller;

import com.sz.demo.aop.SpringUtil;
import com.sz.demo.domain.SmallCar;
import com.sz.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private SmallCar car;

    @Autowired
    private CarService carService;

//    @org.junit.Test
    // yes
    @RequestMapping("/aop")
    public void aop(){
        SpringUtil.getBean(SmallCar.class).testAop();
    }


    // yes
    @RequestMapping("/carDrive")
    public void carDrive(){
        carService.driveCar("hhhh");
    }

    // no
    @RequestMapping("/returnDrive")
    public String returnDrive(){
        System.out.println("returnDrive");
        return "success";
    }

    // no
    @RequestMapping("/drive")
    public void drive(){
        System.out.println("drive");
    }

    // yes
    @RequestMapping("/toCar")
    public void toCar(){
        System.out.println(car.getName());
    }

    // no
    @RequestMapping("/newCar")
    public void newCar(){
        SmallCar newCar = new SmallCar();
        System.out.println("新车");
        System.out.println(newCar.getName());
    }

}
