package com.noob.algorithm.daily.design._3template;

/**
 * 模板方法模式
 * 强调的是操作的流程化。比如要完成一件事情，通过模板约定方法的操作操作流程和规范
 * 子类可以继承父类的流程，也可以自由扩展新的流程逻辑实现
 */

// 定义模板类：约定流程处理
abstract class Template {
    public void doSth() {
        // 按部就班分步执行
        start();
        step1();
        step2();
        end();
    }

    public void start() {
        System.out.println("default start");
    }

    public void step1() {
        System.out.println("default step1");
    }

    public abstract void step2();

    public void end() {
        System.out.println("end");
    }
}

// 定义子类扩展模板
class ATemplate extends Template {

    @Override
    public void step2() {
        System.out.println("AAA step2");
    }
    // 也可按需适当重写父类的方法

    @Override
    public void end() {
        super.end();
        System.out.println("AAAAAA");
    }
}

public class TemplateDemo {
    public static void main(String[] args) {
        ATemplate aTemplate = new ATemplate();
        aTemplate.doSth();
    }
}
