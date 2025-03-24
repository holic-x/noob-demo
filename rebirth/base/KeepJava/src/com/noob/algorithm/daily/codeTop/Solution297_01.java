package com.noob.algorithm.daily.codeTop;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🔴 297 二叉树的序列化与反序列化 - https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/description/
 */
public class Solution297_01 {
}


class Codec {

    List<Integer> ans = new ArrayList<>();

    // 递归遍历二叉树进行序列化操作
    private void doSerialize(TreeNode node) {
        // 递归出口
        if (node == null) {
            ans.add(null); // 空节点表示
            return;
        }
        // node 不为null，处理val并递归其左右子节点
        ans.add(node.val);
        doSerialize(node.left);
        doSerialize(node.right);
    }

    // (1)将一个二叉树【序列化】为String字符串形式
    public String serialize(TreeNode root) {
        doSerialize(root);
        return String.valueOf(ans);
    }


    /**
     * 构造二叉树（根据传入的【字符串序列】构造一棵二叉树）
     */
    private TreeNode buildHelper(List<Integer> list,int left,int right){
        // 校验有效构建范围
        if(left<right){
            return null;
        }
        // 构建节点
        TreeNode node = new TreeNode(list.get(left));
        // 递归构建左右子节点
        node.left = buildHelper(list,left+1,);
    }



    // (2)将一个String形式的内容【反序列化】为二叉树
    public TreeNode deserialize(String data) {

    }
}


// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));