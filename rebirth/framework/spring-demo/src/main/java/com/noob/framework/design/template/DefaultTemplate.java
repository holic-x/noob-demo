package com.noob.framework.design.template;

// 默认模板实现类定义
public class DefaultTemplate extends Template{
    @Override
    protected void primitiveOperation2() {
        System.out.println("默认模板实现类-基本操作方法2执行");
    }

    @Override
    protected void primitiveOperation3() {
        System.out.println("默认模板实现类-基本操作方法3执行");
    }
}
