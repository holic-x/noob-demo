package com.noob.base.batchHandle.util;

import cn.hutool.core.util.RandomUtil;

import java.util.Random;
import java.util.UUID;

/**
 * 随机生成工具类
 */
public class RandomGenUtil {

    public static String genUuid(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String genUuid(int length){
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0,length);
    }

    public static int genNum(int length){
        Random random = new Random();
        int randomNumber = random.nextInt(9 * length) + 10 * length; // 如果length为5，随机生成100000到999999之间的随机数
        return randomNumber;
    }

    public static String genEmail(String domain){
        return RandomUtil.randomString(8) + "@" + domain;
    }

    public static String genMobile(){
        String[] start = {"130", "131", "132", "133", "134", "150", "151", "155", "158", "166", "180", "181", "184", "185", "188"};
        return start[(int) (Math.random() * start.length)]+(10000000+(int)(Math.random()*(99999999-10000000+1)));
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            System.out.println(RandomGenUtil.genNum(6));
        }
    }

}
