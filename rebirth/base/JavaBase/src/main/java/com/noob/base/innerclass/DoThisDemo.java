package com.noob.base.innerclass;


class DoThis{

    void doSth(){
        System.out.println("DoThis doSth");
    }

    // 定义内部类
    class InnerClass{
        // 在内部类中访问外部类
        public DoThis outer(){
            return DoThis.this;
        }
    }

    // 定义方法获取内部类对象
    public InnerClass inner(){
        return new InnerClass();
    }

}

/**
 * inner class demo（.this使用）
 */
public class DoThisDemo {
    public static void main(String[] args) {
        DoThis dt = new DoThis();
        DoThis.InnerClass dti = dt.inner();
        dti.outer().doSth();
    }
}
