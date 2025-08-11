package com.noob.algorithm.daily.plan03.hot100_daily.day06.p015;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡 093 复原IP地址 - https://leetcode.cn/problems/restore-ip-addresses/description/
 */
public class Solution093_01 {

    /**
     * 返回所有可能的有效 IP 地址
     *
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        backTrack(s, 0);
        return ans;
    }

    /**
     * 回溯处理思路
     */
    private List<String> ans = new ArrayList<>();
    private List<String> path = new ArrayList<>();

    private void backTrack(String s, int idx) {

        // 递归出口: 遍历到末尾且路径恰好划分为满足IP规则的内容则收集结果
        if (path.size() == 4 && idx == s.length()) {
            // 校验路径中的每个值是否均满足条件（或者在递归时进行剪枝，确保最终得到的截断均满足IP定义）
            ans.add(String.join(".", path));
        }

        // 回溯处理:此处for循环补充一个循环终止条件，当path.size到达4时可提前终止循环（因为IP只有4段，如果当前切割方案已经有4段，那么后面的切割尝试都是无意义的）
        for (int i = idx; i < s.length() && path.size() < 4; i++) {
            // 上一个截断位置idx =》 当前i指向截断位置的字符串 载入路径（如果不满足IP限制条件则跳过），也可以理解为当前切割位置idx开始寻找下一个满足切割条件的子串的切割位置
            String subStr = s.substring(idx, i + 1);
            if ("0".equals(subStr) || (!subStr.startsWith("0") && subStr.length() <= 3 && Integer.valueOf(subStr).intValue() < 256)) {
                path.add(subStr);
                backTrack(s, i + 1);
                path.remove(path.size() - 1);
            }
        }

    }

}
