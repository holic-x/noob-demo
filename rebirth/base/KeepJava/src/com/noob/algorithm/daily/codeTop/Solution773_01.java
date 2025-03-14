package com.noob.algorithm.daily.codeTop;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


/**
 * 🔴 773 滑动谜题 - https://leetcode.cn/problems/sliding-puzzle/description/
 */
//leetcode submit region begin(Prohibit modification and deletion)
class Solution773_01 {

    // 定义目标状态拼图（2*3）
    int[][] targetBoard = new int[][]{
            {1, 2, 3}, {4, 5, 0}
    };

    // 定义4个移动方向
    int[][] dirs = new int[][]{
            {-1, 0}, {0, -1}, {1, 0}, {0, 1}
    };

    // BFS 方式检索
    public int slidingPuzzle(int[][] board) {
        // 将初始状态和目标状态转化为字符串形式
        String start = boardToStr(board);
        String target = boardToStr(targetBoard);

        // 定义队列辅助遍历
        Queue<String> queue = new LinkedList<>();
        queue.offer(start); // 初始化

        // 记录已经访问过的状态
        Set<String> visited = new HashSet<>();
        visited.add(start);

        int moves = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                if (current.equals(target)) { // 如果当前遍历的序列和目标序列一致，说明可以通过移动得到目标序列，直接返回移动次数
                    return moves;
                }

                // 找到空格的位置
                int zeroIndex = current.indexOf('0'); // 定位字符串序列中'0'的位置（行、列）
                int x = zeroIndex / 3; // 计算行号
                int y = zeroIndex % 3; // 计算列号

                // 尝试四个方向的移动（往4个方向移动空格板）
                for (int[] dir : dirs) {
                    int newX = x + dir[0];
                    int newY = y + dir[1];

                    // 检查新位置是否在边界内
                    if (newX >= 0 && newX < 2 && newY >= 0 && newY < 3) {
                        // 计算新位置的索引
                        int newIndex = newX * 3 + newY;// 将新位置坐标转化为其在字符串的索引位置

                        // 交换空格和相邻的拼图块
                        String next = swap(current, zeroIndex, newIndex); // 交换相邻的板块，并返回新构成的序列
                        // 去除移动后得到重复的board情况，如果当前得到的序列没有被访问过，则将其进行标记并加入queue
                        if (!visited.contains(next)) {
                            visited.add(next);
                            queue.offer(next);
                        }
                    }
                }
            }
            moves++;
        }
        return -1;
    }

    // 自定义方法将board转化为字符串形式存储(此处不用拘泥于非要转化为什么字符串格式，只要能够用于唯一标记board即可)
    private String boardToStr(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : board) {
            for (int num : row) {
                sb.append(num);
            }
        }
        return sb.toString();
    }

    // 自定义方法 交换字符串中的两个字符
    private String swap(String s, int i, int j) {
        char[] arr = s.toCharArray();
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return new String(arr);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
