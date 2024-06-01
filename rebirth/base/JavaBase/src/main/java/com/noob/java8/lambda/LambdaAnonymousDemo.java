package com.noob.java8.lambda;

// 接口定义
interface MyEventConsumer{
    public void consume(Object event);
}


/**
 * Lambda表达式 VS 匿名类
 */
public class LambdaAnonymousDemo {

    public static void main(String[] args) {
        // 匿名类实现
        MyEventConsumer c1 = new MyEventConsumer() {
            // 定义内部状态
            private int eventState = 0;
            @Override
            public void consume(Object event) {
                System.out.println("匿名类实现" + event.toString() + "-" + (eventState++));
            }
        };
        c1.consume("hh");
        c1.consume("kk");

        // Lambda表达式实现
        MyEventConsumer c2 = (event)->{
            System.out.println("Lambda表达式实现" + event.toString());
        };
        c2.consume("xx");
    }

}
