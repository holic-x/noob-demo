package com.noob.algorithm.daily.day13.design.template_method;

/**
 * 模板方法 简单DEMO
 */
public class SimpleTemplateMethodDemo {

    // ① 抽象父类(定义任务模板)
    static abstract class TaskTemplate{
        // 定义一个总的执行流程
        public void executeTask(){
            init(); // 初始化
            doStep1(); // 执行步骤1
            doSpecStep(); // 执行特定步骤
        }

        public void init(){
            System.out.println("init...");
        }
        public void doStep1(){
            System.out.println("exec task step one");
        }
        public abstract void doSpecStep();
    }

    // ② 具体子类
    static class TaskA extends TaskTemplate{
        // 自定义扩展实现某个步骤
        @Override
        public void doSpecStep() {
            System.out.println("taskA do spec step");
        }
    }
    static class TaskB extends TaskTemplate{

        // taskb有自己的初始化机制(可实现方法扩展)
        @Override
        public void init() {
            System.out.println("task B init....");
//            super.init();
        }

        @Override
        public void doSpecStep() {
            System.out.println("taskB do spec step");
        }
    }

    public static void main(String[] args) {
        TaskA taskA = new TaskA();
        taskA.executeTask();
        TaskB taskB = new TaskB();
        taskB.executeTask();
    }
}
