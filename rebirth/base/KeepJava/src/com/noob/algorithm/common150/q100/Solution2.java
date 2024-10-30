package com.noob.algorithm.common150.q100;

import com.noob.algorithm.common150.base.TreeNode;

/**
 * 100 相同的树
 */
public class Solution2 {

    /**
     * 递归思路：
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 当p、q中存在null节点
        if(p==null||q==null){
            return p==q;
        }
        // p、q都不为null的情况下，进一步比较值，如果不匹配则直接返回false
        if(p.val != q.val){
            return false;
        }

        // 当前比较节点值匹配，继续验证其左节点、右节点
        return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }

}
