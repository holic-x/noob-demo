package com.noob.algorithm.daily.plan02.day06.p015;

import java.util.ArrayList;
import java.util.List;

/**
 * 093 复原IP地址 - https://leetcode.cn/problems/restore-ip-addresses/description/
 */
public class Solution093_01 {

    private List<String> res = new ArrayList<>(); // 定义结果集
    private List<String> path = new ArrayList<>(); // 定义当前路径

    public List<String> restoreIpAddresses(String s) {
        // 调用回溯算法
        backTrack(0, s);
        // 返回结果
        return res;
    }


    // 回溯算法
    private void backTrack(int idx, String s) {
        // 递归出口
        if (idx >= s.length()) {
            // 处理结果集
            if (path.size() == 4) {
                // 字符串s恰好被划分为4个有效数字
                res.add(String.join(".", path)); // 将path列表元素用拼接符.拼接为字符串
            }
            return;
        }

        // 回溯处理
        for (int i = idx; i < s.length(); i++) {
            // 切割的有效范围为[idx,i]
            String subStr = s.substring(idx, i + 1);

            // 此处可以进一步进行截断校验，有效截断是至多3位数
            if (subStr.length() > 3) {
                continue;
            }

            // 校验subStr是否有效
            int subInt = Integer.valueOf(subStr);
            if ("0".equals(subStr) || (subInt != 0 && !subStr.startsWith("0") && subInt <= 255)) {
                // 切割字符串不能包括前导0，要么为0，要么为[1,255]中的不包括前导0的数字
                path.add(subStr);
                // 递归寻找下一个切割位置
                backTrack(i + 1, s);
                // 恢复现场
                path.remove(path.size() - 1);
            }
        }
    }

}
