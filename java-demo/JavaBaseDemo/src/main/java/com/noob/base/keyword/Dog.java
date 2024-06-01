package com.noob.base.keyword;

/**
 * @ClassName Huh-x
 * @Description TODO
 * @Author Huh-x
 * @Date 2024 2024/4/29 12:43
 */
class Dog {
    public String name;
    public int age;

    public Dog(String name, int in_age) {
        this.name = name;
        this.age = in_age;
    }

    public void info() {
        System.out.println(this.name + " " + this.age + " 当前对象的hashCode" + this.hashCode());
    }

    public static void main(String[] args) {
        Dog dog1 = new Dog("大壮",3);
        dog1.info();
        Dog dog2 = new Dog("大黄",2);
        dog2.info();
    }
}
