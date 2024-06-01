package com.noob.base.exception;

/**
 * 异常耗时 测试demo
 */
public class ExceptionTimeConsumeDemo {

    private int testTimes;

    public ExceptionTimeConsumeDemo(int testTimes) {
        this.testTimes = testTimes;
    }

    public void newObject() {
        long l = System.nanoTime();
        for (int i = 0; i < testTimes; i++) {
            new Object();
        }
        System.out.println("建立对象：" + (System.nanoTime() - l));
    }

    public void newException() {
        long l = System.nanoTime();
        for (int i = 0; i < testTimes; i++) {
            new Exception();
        }
        System.out.println("建立异常对象：" + (System.nanoTime() - l));
    }

    public void catchException() {
        long l = System.nanoTime();
        for (int i = 0; i < testTimes; i++) {
            try {
                throw new Exception();
            } catch (Exception e) {
            }
        }
        System.out.println("建立、抛出并接住异常对象：" + (System.nanoTime() - l));
    }

    public static void main(String[] args) {
        ExceptionTimeConsumeDemo test = new ExceptionTimeConsumeDemo(10000);
        test.newObject();
        test.newException();
        test.catchException();
    }

}
