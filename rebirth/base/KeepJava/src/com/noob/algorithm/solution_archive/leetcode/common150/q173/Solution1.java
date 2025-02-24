package com.noob.algorithm.solution_archive.leetcode.common150.q173;

import com.noob.algorithm.solution_archive.leetcode.common150.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 173 二叉搜索树迭代器
 */
class BSTIterator {

    List<Integer> list ;// 存储LDR中序遍历结果

    int point ; // 指针

    public BSTIterator(TreeNode root) {
        list = new ArrayList<>();
        point = 0; // 需初始化为一个不存在于bst中的数字，且该数字小于bst中任何元素
        ldr(root,list);
    }

    /**
     * 中序遍历
     */
    public void ldr(TreeNode root,List<Integer> list) {
        if (root == null) {
            return;
        }
        // LDR：left->ROOT->RIGHT
        ldr(root.left,list);
        list.add(root.val);
        ldr(root.right,list);
    }


    public int next() {
        if(point<list.size()){
            return list.get(point++); // 返回值，然后 point 指向指针移动
        }else{
            return -1; // 不存在
        }
    }

    public boolean hasNext() {
        return point<list.size(); // point超出listSize说明不存在next
    }
}
