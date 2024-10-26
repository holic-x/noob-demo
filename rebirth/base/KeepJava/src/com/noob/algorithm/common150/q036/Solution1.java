package com.noob.algorithm.common150.q036;

/**
 * 036 有效的数独
 */
public class Solution1 {
    /**
     * 标记法：三张哈希表用于判断对应位置是否已经出现过该数字
     * 1.数独是9*9表格，因此在遍历的过程中可以将数独的数字和对应矩阵的下标进行对照
     * 2.借助哈希表存储指定位置上数字是否出现过的标记
     * - 1-9 在每行只能出现一次
     * - 1-9 在每列只能出现一次
     * - 1-9 在每个9宫格只能出现一次
     */
    public boolean isValidSudoku(char[][] board) {

        // 定义哈希表存储数字出现的标记
        boolean[][] rowFlag = new boolean[9][10]; // 表示某一行的每个数是否出现过，取下标1-9的位置操作
        boolean[][] colFlag = new boolean[9][10]; // 表示某一列的每个数是否出现过，取下标1-9的位置操作
        boolean[][] boxFlag = new boolean[9][10]; // 表示某个box的每个数是否出现过，取下标1-9的位置操作

        // 遍历矩阵元素，依次进行判断
        int m = board.length, n = board[0].length;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // 判断某行某列的对应元素在相应的区域内是否已经出现过
                if (board[i][j] == '.') {
                    continue; // '.' 表示待填充区域跳过当次判断
                }
                // 将对应数字字符串转化为int类型
                int curNumber = board[i][j] - '0';
                // 将数字与对应的哈希表存储位置对照
                if (rowFlag[i][curNumber]) { // 校验规则1：判断行
                    return false; // 表示第i行的当前数字已经出现过，直接返回false
                } else {
                    rowFlag[i][curNumber] = true; // 未出现过，则进行标记
                }

                if (colFlag[j][curNumber]) { // 校验规则2：判断列
                    return false; // 表示第j列的当前数字已经出现过，直接返回false
                } else {
                    colFlag[j][curNumber] = true; // 未出现过，则进行标记
                }

                if (boxFlag[j / 3 + (i / 3) * 3][curNumber]) {
                    return false; // 表示对应box的当前数字已经出现过，直接返回false
                } else {
                    boxFlag[j / 3 + (i / 3) * 3][curNumber] = true; // 未出现过，则进行标记
                }
            }
        }
        return true;
    }


}
