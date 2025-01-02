package com.noob.algorithm.daily.plan01.day19;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡 093 复原IP地址
 */
public class Solution093_01 {

    public List<String> res = new ArrayList<>(); // 记录结果集
    public List<String> curList = new ArrayList<>(); // 记录当前的切割方案

    public List<String> restoreIpAddresses(String s) {
        // 调用回溯算法
        backTrack(s, 0);
        // 返回结果集
        return res;
    }

    // 回溯算法处理
    public void backTrack(String s, int index) {
        // 如果切割位置到达字符串尾部则结束
        if (index == s.length()) {
            if (curList.size() == 4) { // 不包含前导0,分割为4个整数
                res.add(String.join(".", curList)); // 载入结果
            }
            return;
        }

        for (int i = index; i < s.length(); i++) {
            // 校验当前切割部分是否满足要求（不能含有前导0，整数在0-255间取值）
            String subStr = s.substring(index, i + 1); // 选定切割位置，校验切割位置是否满足条件
            if (!subStr.equals("0") && subStr.startsWith("0")) {
                continue;
            }
            long subInt = Long.valueOf(subStr);
            if (subInt >= 0 && subInt <= 255) {
                // 满足条件，递归寻找下一个切割位置
                curList.add(subStr);
                backTrack(s, i + 1);
                curList.remove(curList.size() - 1);
            }
        }
    }

}
