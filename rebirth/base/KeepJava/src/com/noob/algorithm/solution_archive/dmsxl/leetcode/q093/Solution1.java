package com.noob.algorithm.solution_archive.dmsxl.leetcode.q093;

import java.util.ArrayList;
import java.util.List;

/**
 * 093 复原IP地址
 */
public class Solution1 {

    List<String> res = new ArrayList<>(); // 定义结果集
    List<String> temp = new ArrayList<>(); // 定义临时结果（可以理解为切割路径）

    public List<String> restoreIpAddresses(String s) {
        // 长度过滤(相当于剪枝)
        if(s == null || s.length() < 4 || s.length() > 16){
            return new ArrayList<>();
        }

        // 执行回溯方法
        backTrack(s, 0);
        // 返回结果
        return res;
    }

    /**
     * 回溯算法
     */
    public void backTrack(String s, int idx) {
        // 当idx遍历到字符串末尾则结束
        if (idx == s.length()) {
            // 判断当前是否切割为4份
            if (temp.size() == 4) {
                // 满足条件，载入结果集
                res.add(String.join(".", temp));
            }
            // 退出
            return;
        }

        // 回溯过程
        for (int i = idx; i < s.length(); i++) {
            // 从当前切割位置开始，找到第一个满足切割条件的子串，然后定位下一个切割位置，递归+回溯
            String subStr = s.substring(idx, i + 1); // 注意substring切割范围：[startIndex,endIndex)
            if (subStr.equals("0") || (!subStr.startsWith("0") && i<idx+4 && Integer.valueOf(subStr) <= 255)) {
                // 满足条件则处理节点，并执行递归+回溯
                temp.add(subStr);
                backTrack(s, i + 1); // 从下一个位置开始判断切割点（不能在重复位置切割）
                temp.remove(temp.size() - 1); // 复原现场
            }
        }
    }

}
