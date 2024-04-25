package com.noob.demo.observer.trafficLight;

public class Demo {
    public static void main(String[] args) {
        // 创建被观察者对象（红绿灯对象）
        TrafficLight trafficLight = new TrafficLight();

        // 创建观察者对象（行人和司机）
        Driver driver = new Driver();
        Pedestrian pedestrian = new Pedestrian();

        // 添加观察者对象
        trafficLight.addObserver(driver);
        trafficLight.addObserver(pedestrian);

        // 红绿灯状态改变需通知其他观察者
        trafficLight.informObservers("红绿灯状态改变");
        System.out.println("........................");
        trafficLight.informObservers("红绿灯状态再次改变了");
        System.out.println("........................");

        // 司机开走了,移除司机观察者,随后再次通知
        trafficLight.deleteObserver(driver);
        trafficLight.informObservers("红绿灯状态第三次改变");
    }
}
