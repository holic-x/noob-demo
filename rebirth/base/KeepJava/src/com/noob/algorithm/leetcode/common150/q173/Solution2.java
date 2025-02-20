package com.noob.algorithm.leetcode.common150.q173;

import com.noob.algorithm.leetcode.common150.base.TreeNode;

import java.util.Stack;

/**
 * 173 二叉搜索树迭代器
 */
class BSTIterator1 {

    Stack<TreeNode> stack; // 栈存储待遍历的元素

    TreeNode cur;// 指向当前节点

    public BSTIterator1(TreeNode root) {
        stack = new Stack<>();
        cur = root; // 指针初始化
    }

    public int next() {
        // 当cur不为空，一直遍历左子树(直到cur为null退出)
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        // 节点为空则弹出栈顶元素
        cur = stack.pop();
        int val = cur.val;

        // 遍历右节点
        cur = cur.right;
        return val;
    }

    public boolean hasNext() {
        // 当指针节点为null且栈为空表示遍历完毕
        return cur != null || !stack.isEmpty();
    }

}
