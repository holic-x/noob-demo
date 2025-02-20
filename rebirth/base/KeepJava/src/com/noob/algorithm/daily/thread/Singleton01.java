package com.noob.algorithm.daily.thread;

import com.noob.scene.redis.Singleton;

/**
 * 12.java 实现单例模式
 * - 懒汉式（只有在使用到的时候才创建对象）
 */
public class Singleton01 {

    // 1.构建一个静态的私有的自身的对象
    private static Singleton01 singleton;

    // 2.构造函数私有化
    public Singleton01() {
    }

    // 3.对外提供一个获取该单例对象的入口
    public static Singleton01 getSingleton(){
        if(singleton==null){
            singleton = new Singleton01();
        }
        return singleton;
    }
}
