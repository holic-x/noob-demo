package com.noob.base.innerclass;

// 案例1：接口内部的类
interface InInterfaceClass{
    void show();
    // 内部类
    class InnerClass implements InInterfaceClass{
        // 可以在接口内部类中实现其外部接口
        @Override
        public void show() {
            System.out.println("in interface inner class");
        }
    }

    public static void main(String[] args) {
        InInterfaceClass inInterfaceClass = new InnerClass();
        inInterfaceClass.show();
    }
}

// 案例2：测试代码（定义嵌套类存放测试代码）
class TestBed {
    public void f() { System.out.println("f()"); }
    public static class Tester {
        public static void main(String[] args) {
            TestBed t = new TestBed();
            t.f();
        }
    }
}

// 案例3：多层嵌套类
class MNA {
    private void f() {}
    class A {
        private void g() {}
        public class B {
            void h() {
                g();
                f();
            }
        }
    }

    public static void main(String[] args) {
        MNA mna = new MNA();
        MNA.A mnaa = mna.new A();
        MNA.A.B mnaab = mnaa.new B();
        mnaab.h();
    }
}

/**
 * 嵌套类 demo
 */
public class NestClassDemo {



}
