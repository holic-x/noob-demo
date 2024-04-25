package com.noob.demo.observer.trafficLight;

/**
 * 被观察者：接口定义（添加、移除、通知观察者对象）
 */
public interface Subject {
    /**
     * 添加观察者
     * @param observer  观察者对象
     */
    void addObserver(Observer observer);
    /**
     * 删除观察者
     * @param observer  观察者对象
     */
    void deleteObserver(Observer observer);
    /**
     * 通知所有的观察者
     * @param message  通知
     */
    void informObservers(String message);
}
