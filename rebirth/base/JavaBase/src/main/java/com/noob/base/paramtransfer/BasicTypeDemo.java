package com.noob.base.paramtransfer;

/**
 * 基本数据类型
 */
public class BasicTypeDemo {

    public static void update(int count){
        // 修改count的值
        count ++ ;
        System.out.println("update count:"+count);
    }

    public static void main(String[] args) {
        int count = 0;
        update(count);
        System.out.println("main count:"+count);
    }
}
