package com.noob.algorithm.plan_archive.plan01.day12;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟡513 找树左下角的值
 */
public class Solution513_03 {

    // 全局维护maxLevel、target
    public int maxLevel = -1; // 表示当前遍历的最大层数
    public int target = -1; // 始终指向当前遍历层的第1个元素（与maxLevel同步更新）

    // dfs：维护maxLevel和target
    public int findBottomLeftValue(TreeNode root) {
        // 调用dfs方法处理
        dfs(root, 0);

        // 返回结果(当所有节点遍历完成，此时target指向遍历的最后一层的第1个元素)
        return target;
    }

    public void dfs(TreeNode node, int curLevel) {
        if (node == null) {
            return;
        }

        // 处理节点(判断是否要更新层数)
        if (curLevel > maxLevel) {
            // 说明需要更新最大层数并同步更新target指向每层的第1个元素(表示遍历到这个元素的时候需要跳层)
            maxLevel = curLevel;
            target = node.val;
        }

        // 递归处理左、右节点
        dfs(node.left, curLevel + 1);
        dfs(node.right, curLevel + 1);
    }
}
