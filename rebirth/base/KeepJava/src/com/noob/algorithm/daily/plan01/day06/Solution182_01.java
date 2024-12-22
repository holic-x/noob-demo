package com.noob.algorithm.daily.plan01.day06;

/**
 * 🟢 LRC 182-动态口令
 */
public class Solution182_01 {

    /**
     * 将字符串的前target个元素移动到password后面
     */
    public String dynamicPassword(String password, int target) {
        // 构建字符串
        StringBuffer res = new StringBuffer();
        // 先遍历[target,len-1]范围
        for (int i = target; i <= password.length() - 1; i++) {
            res.append(password.charAt(i));
        }
        // 追加[0,target-1]范围
        for (int i = 0; i < target; i++) {
            res.append(password.charAt(i));
        }
        // 返回结果
        return res.toString();
    }

}
