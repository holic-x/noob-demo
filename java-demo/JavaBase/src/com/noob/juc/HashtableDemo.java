package com.noob.juc;

import java.util.Hashtable;

/**
 * @description:
 * @author：holic-x
 * @date: 2018/4/20
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class HashtableDemo  {

    public static void main(String[] args) throws InterruptedException {
        Hashtable<String,String> hm = new Hashtable<>();

        Thread t1 = new Thread(()->{
          for (int i = 0 ;i<25;i++){
              hm.put(""+i,"线程"+i);
          }
        });

        Thread t2 = new Thread(()->{
            for (int i = 25 ;i<50;i++){
                hm.put(""+i,"线程"+i);
            }
        });

        // 启动线程，并通过适当设定sleep确保数据可正常添加完成
        t1.start();
        t2.start();
        Thread.sleep(1000);

        // 循环打印Hashtable集合的内容
        for (int i=0;i<50;i++){
            System.out.println(hm.get(i+""));
        }
    }
}
