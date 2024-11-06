package com.noob.algorithm.common150.q427;

/**
 * 427 建立四叉树
 */
public class Solution1 {

    public Node construct(int[][] grid) {
        return build(grid, 0, grid.length - 1, 0, grid[0].length - 1);
    }

    public Node build(int[][] grid, int rowStart, int rowEnd, int colStart, int colEnd) {
        // 如果当前矩阵元素均相同，则构建节点（表示一个叶子节点）
        if (allSame(grid, rowStart, rowEnd, colStart, colEnd)) {
            return new Node(grid[rowStart][colStart] == 1 ? true : false, true); // 设置val(1为true 0为false)、 isLeaf(全区域值相同表示为叶子节点)
        }

        // 非叶子节点的处理，需要进一步递归拆分
        int rowMid = (rowStart + rowEnd) / 2;
        int colMid = (colStart + colEnd) / 2;

        // 构建节点(闭区间写法)
        Node topLeft = build(grid, rowStart, rowMid, colStart, colMid);// 上左
        Node topRight = build(grid, rowStart, rowMid, colMid + 1, colEnd); // 上右
        Node bottomLeft = build(grid, rowMid + 1, rowEnd, colStart, colMid); // 下左
        Node bottomRight = build(grid, rowMid + 1, rowEnd, colMid + 1, colEnd); // 下右

        // 返回构建好的节点
        return new Node(true, false, topLeft, topRight, bottomLeft, bottomRight);
    }

    /**
     * 判断指定区域的值是否完全相同
     * row: 从上到下
     * col：从左到右
     */
    public boolean allSame(int[][] grid, int rowStart, int rowEnd, int colStart, int colEnd) {
        //判断矩阵某区域是否同值
        for (int i = rowStart; i <= rowEnd; i++) {
            for (int j = colStart; j <= colEnd; j++) {
                if (grid[i][j] != grid[rowStart][colStart]) { // 判断每个元素是否和矩阵第一个元素相同
                    return false;
                }
            }
        }
        return true;
    }

}


/**
 * 四叉树节点定义
 */
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;


    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}