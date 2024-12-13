package com.noob.algorithm.dmsxl.leetcode.q098;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 098 验证二叉搜索树
 */
public class Solution1 {

    // DFS 递归法
    public boolean isValidBST(TreeNode root) {
        if(root==null){
            return true;
        }
        List<Integer> list = new ArrayList<>();
        dfs(root,list);
        // 验证中序序列是否升序
        for(int i=0;i<list.size()-1;i++){
            if(list.get(i)>=list.get(i+1)){ // 出现相等或者降序的情况
                return false;
            }
        }
        // 验证通过
        return true;
    }


    // 中序遍历验证升序
    public void dfs(TreeNode node, List<Integer> list){
        if(node==null){
            return;
        }

        // LDR 中序遍历
        dfs(node.left,list);
        list.add(node.val);
        dfs(node.right,list);
    }

}
