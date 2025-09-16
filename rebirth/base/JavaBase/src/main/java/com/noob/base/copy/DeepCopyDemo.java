package com.noob.base.copy;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class Money {
    private String unit;
    private int amount;
}

@Data
@AllArgsConstructor
class Employee implements Cloneable { // 被复制的目标类需实现Cloneable接口
    private String name;
    private int age;
    private Money money;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // 在clone中进行深度复制
        // Employee cloned = (Employee) super.clone(); 直接调用父类的clone实现基础类型的clone
        Money newMoney = new Money(this.getMoney().getUnit(), this.getMoney().getAmount());
        Employee employee = new Employee(this.getName(), this.getAge(), newMoney);
        return employee;
    }
}

/**
 * 深拷贝
 */
public class DeepCopyDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        Employee e1 = new Employee("huh", 18, new Money("HSD", 1000));
        Employee e2 = (Employee) e1.clone();
        System.out.println(e1.getName().equals(e2.getName()));// true
        System.out.println(e1.getAge() == e2.getAge()); // true
        System.out.println(e1.getMoney() == e2.getMoney()); // false

        // 尝试修改Money属性
        e1.getMoney().setUnit("RMB");
        e1.getMoney().setAmount(30);
        System.out.println(e1.getMoney().getUnit() + "-" + e1.getMoney().getAmount()); // RMB-30
        System.out.println(e2.getMoney().getUnit() + "-" + e2.getMoney().getAmount()); // HSD-1000

        /**
         * 结合代码分析，在Employee中实现Cloneable接口并重写了clone实现深度拷贝，自定义深度拷贝逻辑
         * 因此此处可以通过结果观察，e1的Money对象属性修改并没有影响到e2，说明这两个对象的内部引用的Money已经是各自独立的，这是深拷贝的概念
         */
    }
}
