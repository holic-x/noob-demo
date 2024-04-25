package com.noob.demo.observer.trafficLight;

/**
 * 观察者：司机
 */
public class Driver implements Observer{
    @Override
    public void change(String message) {
        System.out.println("司机观察到"+message);
    }
}
