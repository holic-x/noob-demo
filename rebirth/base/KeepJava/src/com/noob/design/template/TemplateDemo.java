package com.noob.design.template;

/**
 * 模板方法:针对一系列流程类、步骤性比较强而又需要支持灵活扩展的业务场景
 * - 抽象父类：定义模板规范（例如某些任务的执行步骤）
 * - 具体子类：继承抽象父类，在保留父类模板方法的同时，又可以支持灵活扩展自定义步骤
 */
public class TemplateDemo {
    public static void main(String[] args) {
        // 测试实现
        ATaskExecutor aTaskExecutor = new ATaskExecutor();
        aTaskExecutor.executeTask(); // 调用综合方法

        System.out.println("----------------------------------");

        BTaskExecutor bTaskExecutor = new BTaskExecutor();
        bTaskExecutor.executeTask(); // 调用综合方法
    }
}

// 抽象父类：例如此处定义父类模板，主要用于任务执行的步骤规范定义
abstract class TaskTemplate{

    // 定义一个总的执行流程，然后拆分这些节点的实现（哪些是公共的，哪些是需要自定义扩展的）
    public void executeTask(){
        // 初始化
        init();
        // 执行步骤1
        doStepOne();
        // 执行步骤2(这个部分可以有不同的实现扩展)
        doStepTwo();
        // 结束
        System.out.println("task end");
    }

    // 初始化
    public void init(){
        System.out.println("task init");
    }

    // 执行步骤1
    public void doStepOne(){
        System.out.println("execute step one");
    }

    // 执行步骤2（模拟每个步骤下不同的执行者有不同的执行逻辑）
    public abstract void doStepTwo();

}

// 定义具体子类自行扩展父类(继承)
class ATaskExecutor extends TaskTemplate{
    @Override
    public void doStepTwo() {
        System.out.println("execute step two by ATaskExecutor");
    }
}
class BTaskExecutor extends TaskTemplate{
    // 除了强制实现的步骤之外，B还希望不用模板的默认方法，要自己重写step one
    @Override
    public void doStepOne() {
        System.out.println("execute step one by BTaskExecutor 6666666");
    }

    @Override
    public void doStepTwo() {
        System.out.println("execute step two by BTaskExecutor");
    }

}
