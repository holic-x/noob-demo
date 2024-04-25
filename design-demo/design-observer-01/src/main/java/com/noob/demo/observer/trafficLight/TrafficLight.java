package com.noob.demo.observer.trafficLight;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者对象实现：红绿灯
 */
public class TrafficLight implements Subject {

    // 存放观察者集合
    private List<Observer> observerList = new ArrayList<>();

    // 添加观察者
    @Override
    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    // 删除观察者
    @Override
    public void deleteObserver(Observer observer) {
        observerList.remove(observer);
    }

    // 通知
    @Override
    public void informObservers(String message) {
        for (Observer observer : observerList) {
            observer.change(message);
        }
    }
}
