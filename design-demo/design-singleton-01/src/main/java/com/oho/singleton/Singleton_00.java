package com.oho.singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 00.静态类使用
 * 第一次运行的时候直接初始化Map
 */
public class Singleton_00 {

    public static Map<String,String> cache = new ConcurrentHashMap<String, String>();

}
