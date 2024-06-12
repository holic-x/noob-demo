package com.noob.framework.design.template;

// 抽象模板方法
public abstract class Template {
    // 定义模板方法（可以理解为一组操作流程的规则定义）
    public final void templateMethod(){
        // 按照一定规则执行操作
        primitiveOperation1();
        primitiveOperation2();
        primitiveOperation3();
    }

    // 当前类实现
    protected void primitiveOperation1(){
        System.out.println("Template默认实现：基本操作1");
    }

    // 子类的扩展实现
    protected abstract void primitiveOperation2();
    protected abstract void primitiveOperation3();
}
