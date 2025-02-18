package com.noob.algorithm.daily.archive.plan01.day06;

/**
 * 🟢 LCR 122-路径加密
 */
public class Solution122_01 {

    /**
     * 将字符串中的`.`替换为空格,返回加密后的字符串
     */
    public String pathEncryption(String path) {
        while (path.contains(".")) {
            path = path.replace("."," "); // " " 空格 \s
        }
        return path;
    }

}
