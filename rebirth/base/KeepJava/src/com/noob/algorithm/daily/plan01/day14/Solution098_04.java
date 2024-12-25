package com.noob.algorithm.daily.plan01.day14;

import com.noob.algorithm.daily.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡 098 验证二叉搜索树
 */
public class Solution098_04 {

    public boolean isValidBST(TreeNode root) {
        // return dfs(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE); // 注意数值取值范围
    }

    // 递归处理
    public boolean dfs(TreeNode node, long minVal, long maxVal) {
        if (node == null) {
            return true;
        }

        int cur = node.val;

        // 节点需满足cur属于(minVal，maxVal)范围，且其左右子树也要满足相关条件
        return cur > minVal && cur < maxVal && dfs(node.left, minVal, cur) && dfs(node.right, cur, maxVal);
    }
}
