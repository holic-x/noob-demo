package com.noob.juc;


import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author：holic-x
 * @date: 2018/4/20
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class ConcurrentHashMapDemo {

    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String,String> chm = new ConcurrentHashMap<>();

        Thread t1 = new Thread(()->{
            for (int i = 0 ;i<25;i++){
                chm.put(""+i,"线程"+i);
            }
        });

        Thread t2 = new Thread(()->{
            for (int i = 25 ;i<50;i++){
                chm.put(""+i,"线程"+i);
            }
        });

        // 启动线程，并通过适当设定sleep确保数据可正常添加完成
        t1.start();
        t2.start();
        Thread.sleep(1000);

        // 循环打印Hashtable集合的内容
        for (int i=0;i<50;i++){
            System.out.println(chm.get(i+""));
        }
    }
}
