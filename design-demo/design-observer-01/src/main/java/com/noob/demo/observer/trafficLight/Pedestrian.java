package com.noob.demo.observer.trafficLight;

/**
 * 观察者：行人
 */
public class Pedestrian implements Observer{
    @Override
    public void change(String message) {
        System.out.println("行人观察到"+message);
    }
}
