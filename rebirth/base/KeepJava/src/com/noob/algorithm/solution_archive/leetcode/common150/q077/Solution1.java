package com.noob.algorithm.solution_archive.leetcode.common150.q077;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 077 组合
 */
public class Solution1 {

    // 全局定义返回结果
    List<List<Integer>> ans = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>(); // 借助队列存储遍历路径的临时结果

    // 回溯方法定义：idx 指向当前可遍历的索引起点
    public void backTrack(int idx, int n, int k) {
        // 当有k个数则加入结果集（递归终止条件：path的长度达到k）
        if (k == path.size()) {
            ans.add(new ArrayList<>(path)); // new 一个队列（如果传入的path是地址，后面对path的修改也会体现，因此此处是new）
            return;
        }
        // 从[i,n]中递归（遍历可能的搜索起点：idx开始）
        for (int i = idx; i <= n; i++) {
            path.add(i); // 添加
            backTrack(i + 1, n, k); // 递归（设置搜索起点+1，因为组合中不允许出现重复的元素）
            path.removeLast(); // 逆向操作（深度优先遍历有回头的过程，递归之前做了什么，递归之后此处需要做同操作的逆向操作）
        }
    }

    // 回溯法
    public List<List<Integer>> combine(int n, int k) {
        // 特例：如果k<=0或者n<k 返回空集合
        // 其他情况进行递归
        backTrack(1, n, k); // 设定从1开始遍历
        return ans;
    }

}