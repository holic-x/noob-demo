package com.noob.jvm;


import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

class Obj{
    String objName;

    public Obj(String objName) {
        this.objName = objName;
    }

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }
}


// 引用相关（强、软、弱、虚）
public class ReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        // 1.强引用
        Obj obj = new Obj("test");
        // 2.软引用（结合OOM理解，当内存不足的时候JVM会尝试回收弱引用以释放资源）
        SoftReference<Obj> softObj = new SoftReference<Obj>(obj);
        System.out.println(softObj.get().getObjName());


        // 3.弱引用（当对象只有一个弱引用，则只要发生垃圾回收GC就会被回收），弱引用活不过下一次GC
        WeakReference<String> weakObj = new WeakReference<>(new String("hello"));
        System.out.println("GC执行前："+weakObj.get());
        // 主动通知JVM执行GC操作
        System.gc();
//        Thread.sleep(1000);
        System.out.println("GC执行后：" + weakObj.get());


        // 强引用和弱引用 结合使用demo
        Obj sObj = new Obj("Strong"); // 创建了一个Obj对象的强引用
        WeakReference<Obj> wrObj = new WeakReference<>(sObj); // 创建了Obj对象的弱引用
        System.out.println("调用GC前：" + wrObj.get());
        System.gc();
        System.out.println("调用GC后：" + wrObj.get());// 因为这个Obj对象还有一个强引用，所以即使调用gc也不会回收弱引用对象
        // 取消sObj的强引用
        sObj = null;
        System.out.println("取消Obj强引用，调用GC前：" + wrObj.get());
        System.gc();
        System.out.println("取消Obj强引用，调用GC后：" + wrObj.get()); // 此时Obj对象只有一个弱引用，因此调用gc会回收这个弱引用对象

        // 4.虚引用
        ReferenceQueue<String> queue = new ReferenceQueue<>();
        PhantomReference<String> pr = new PhantomReference<>(new String("hello"), queue); // 创建的一个对象的虚引用
        System.out.println(pr.get());
        System.gc();// gc操作时发现它还有虚引用，就把这个虚引用加入到与其关联的引用队列中
        Thread.sleep(1000); // 等待gc完成
        System.out.println(queue.poll()); // 验证虚引用是否被加到指定队列中
    }
}
