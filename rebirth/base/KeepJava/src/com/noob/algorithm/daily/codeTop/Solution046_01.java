package com.noob.algorithm.daily.codeTop;

import java.util.ArrayList;
import java.util.List;

public class Solution046_01 {

    public List<String> res = new ArrayList<>();
    public List<Character> curPath = new ArrayList<>();

    public List<String> permute(char[] chs) {
        // 调用回溯算法
        backTrack(chs, 0);
        // 返回结果
        return res;
    }

    // 全排列：有顺序限定（不同序列表示不同）
    public void backTrack(char[] chs, int index) {
        // 递归出口：全排列的子集大小和chs大小相同（可以理解为一条完整路径）
        if (curPath.size() == chs.length) {
            StringBuilder sb = new StringBuilder();
            for (char c : curPath) {
                sb.append(c);
            }
            res.add(sb.toString());
            return; // 添加 return 语句，避免不必要的递归
        }

        // 回溯处理
        for (int i = index; i < chs.length; i++) {
            // 如果当前字符已经处理过，跳过
            if (i != index && chs[i] == chs[index]) {
                continue;
            }
            // 交换元素并处理
            swap(chs, i, index);
            curPath.add(chs[index]);
            // 递归选择
            backTrack(chs, index + 1);
            // 复原现场
            curPath.remove(curPath.size() - 1);
            swap(chs, i, index);
        }
    }

    // 交换指定位置的元素
    public void swap(char[] chs, int x, int y) {
        char temp = chs[x];
        chs[x] = chs[y];
        chs[y] = temp;
    }

    public static void main(String[] args) {
        String s = "din"; // 测试用例 dinitrophenylhydrazine(全排列计算量非常庞大)
        Solution046_01 solution = new Solution046_01();
        List<String> list = solution.permute(s.toCharArray());
        System.out.println(list);
        System.out.println(list.size());
    }
}