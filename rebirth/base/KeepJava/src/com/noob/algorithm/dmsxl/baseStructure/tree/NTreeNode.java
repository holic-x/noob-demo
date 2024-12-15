package com.noob.algorithm.dmsxl.baseStructure.tree;

import java.util.List;

/**
 * N 叉树节点定义
 */
public class NTreeNode {

    public int val; // 节点值
    public List<NTreeNode> children;

    // 构造函数
    public NTreeNode(){}
    public NTreeNode(int val){
        this.val = val;
    }
    public NTreeNode(int val,List<NTreeNode> children){
        this.val = val;
        this.children = children;
    }
}
