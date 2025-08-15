package com.noob.base.copy;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class Address {
    private String city;
    private String detail;
}

@Data
@AllArgsConstructor
class Person implements Cloneable { // 被复制的目标类需实现Cloneable接口
    private String name;
    private int age;
    private Address address;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // 默认clone
        return super.clone();
    }
}

/**
 * 浅拷贝
 */
public class ShallowCopyDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person p1 = new Person("huh", 18, new Address("上海", "上金大厦"));
        Person p2 = (Person) p1.clone();
        System.out.println(p1.getName().equals(p2.getName()));// true
        System.out.println(p1.getAge() == p2.getAge());// true
        System.out.println(p1.getAddress() == p2.getAddress()); // true

        // 尝试修改地址内容
        p1.getAddress().setCity("深圳");
        System.out.println(p1.getAddress().getCity()); // 深圳
        System.out.println(p2.getAddress().getCity()); // 深圳
        /**
         * 因为p2是由p1通过默认的clone方法克隆得到（clone）：
         * - 对于基础类型：直接copy一份值
         * - 对于String类型：String 被设计为不可变类，浅克隆也是可以直接克隆的
         * - 对于引用类型：copy的是对象的地址引用，所以此处p1、p2实际上处理的是同一个Address引用对象
         * 也就解释了上述操作通过p1修改地址，然后通过p2获取地址的时候拿到的是同样的信息
         */
    }
}
