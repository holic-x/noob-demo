package com.noob.base.innerclass;

// 接口模式：变相实现"多重继承"
interface A{ }
interface B{ }
// 类实现多个接口，结合接口功能，变相实现多继承
class C implements A,B{ }

// 内部类：变相实现"多重继承"
class D{}
abstract class E { }
// 类继承父类并通过内部类（独立继承实现），变相实现多继承
class X extends D {
    E makeE(){
        return new E() {};
    }
}

/**
 * 内部类“多重继承”概念
 */
public class InnerClassMuiltDemo {
    static void takesD(D d) {}
    static void takesE(E e) {}
    public static void main(String[] args) {
        X x = new X();
        takesD(x);
        takesE(x.makeE());
    }
}
