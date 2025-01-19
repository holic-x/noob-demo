package com.noob.algorithm.daily.plan01.archive.day06;

/**
 * 🟢 LCR 122-路径加密
 */
public class Solution122_02 {

    /**
     * 将字符串中的`.`替换为空格,返回加密后的字符串
     * 转化为字符数组进行处理
     */
    public String pathEncryption(String path) {
        char[] arr = path.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '.') {
                arr[i] = ' '; // 替换成空格
            }
        }
        // 返回结果
        return new String(arr);
    }

}
