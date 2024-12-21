package com.noob.algorithm.dmsxl.leetcode.q129;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡129 求根节点道叶子节点生成的所有数字之和
 */
public class Solution1 {

    List<Integer> res = new ArrayList<>(); // res 记录所有路径生成数字
    StringBuffer path = new StringBuffer(); // path 记录当前路径

    public int sumNumbers(TreeNode root) {
        // 初始化先将根节点载入路径
        path.append(root.val);
        // 递归调用
        dfs(root);
        // 遍历获取到的res，计算累加和
        int sum = 0;
        for (int num : res) {
            sum += num;
        }
        return sum;
    }


    // 定义算法处理
    public void dfs(TreeNode node) {
        if (node.left == null && node.right == null) {
            // 记录path
            res.add(Integer.valueOf(path.toString()));
        }

        // 选择路径
        if (node.left != null) {
            path.append(node.left.val);
            dfs(node.left);
            path.deleteCharAt(path.length() - 1);
        }
        if (node.right != null) {
            path.append(node.right.val);
            dfs(node.right);
            path.deleteCharAt(path.length() - 1);
        }
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;
        Solution1 solution1 = new Solution1();
        solution1.sumNumbers(node1);
    }

}
