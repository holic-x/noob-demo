package com.noob.base.innerclass;



class DoNew{
    // 内部类定义
    public class Inner{}
}

/**
 * inner class demo
 */
public class DoNewDemo {
    public static void main(String[] args) {
        DoNew doNew = new DoNew();
        DoNew.Inner inner = doNew.new Inner();
    }
}
