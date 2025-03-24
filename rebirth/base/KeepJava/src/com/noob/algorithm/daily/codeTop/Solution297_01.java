package com.noob.algorithm.daily.codeTop;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 🔴 297 二叉树的序列化与反序列化 - https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/description/
 */
public class Solution297_01 {
}

/*
class Codec {

    StringBuffer ans = new StringBuffer();

    // 递归遍历二叉树进行序列化操作
    private void doSerialize(TreeNode node) {
        // 递归出口
        if (node == null) {
            ans.append("None").append(",");
            return;
        }
        // node 不为null，处理val并递归其左右子节点
        ans.append(node.val).append(",");
        doSerialize(node.left);
        doSerialize(node.right);
    }

    // (1)将一个二叉树【序列化】为String字符串形式
    public String serialize(TreeNode root) {
        doSerialize(root);
        return ans.toString();
    }


    // 构造二叉树（根据传入的【字符串序列】构造一棵二叉树）
    private TreeNode buildHelper(List<String> list) {
        // 选择列表中的第1个元素作为构建节点
        if ("None".equals(list.get(0))) {
            // 取出节点并构建节点
            list.remove(0);
            return null;
        }

        // 构建节点
        TreeNode node = new TreeNode(Integer.valueOf(list.get(0)));
        list.remove(0); // 移除节点
        // 递归构建左右子节点
        node.left = buildHelper(list);
        node.right = buildHelper(list);

        // 返回构建好的节点
        return node;
    }


    // (2)将一个String形式的内容【反序列化】为二叉树
    public TreeNode deserialize(String data) {
        String[] dataArray = data.split(",");
        List<String> dataList = new LinkedList<String>(Arrays.asList(dataArray));
        TreeNode node = buildHelper(dataList);
        return node;
    }
}
*/