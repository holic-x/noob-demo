package com.noob.base.finalize;

/**
 * @ClassName Huh-x
 * @Description TODO
 * @Author Huh-x
 * @Date 2024 2024/4/29 13:20
 */
public class FinalizeDemo {

    public static void main(String[] args) {
        // 定义汽车对象
        Car bmw = new Car("宝马");

        /**
         * 将bmw指向为null
         * 此时bmw对象是一个垃圾，垃圾回收期会自动回收（销毁对象）
         * 在释放对象之前，会调用对象的finalize方法
         * 如果Car类中重写了finalize方法，则会调用自定义的finalize逻辑
         * 如果Car类没有重写finalize方法，则会默认调用Object提供的finalize
         */
        bmw = null;
        // 主动调用垃圾回收器
        System.gc();
        System.out.println("程序退出...");
    }

}

class Car{
    private String name;
    public Car(String name){
        this.name = name;
    }
    // 重写finzlize
    @Override
    protected void finalize() throws Throwable {
        // 自定义实现资源释放操作
        System.out.println("销毁汽车"+name);
        System.out.println("资源释放...");
    }
}


