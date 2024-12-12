package com.noob.algorithm.dmsxl.kmw.q110;

import java.util.*;

/**
 * KMW 110-字符串接龙
 */
public class Solution1 {

    /**
     * 从beginWord->endWord的转化，每次变化只能变化一个字符，且变化后的str需在wordList字符串序列中
     *
     * @param beginWord 源字符串
     * @param endWord   目标字符串
     * @param wordList  字符串序列
     * @return
     */
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        return bfs(beginWord, endWord, wordList);
    }

    /**
     * 广度优先遍历
     */
    public static int bfs(String beginWord, String endWord, List<String> wordList) {
        // 定义visitedSet存储已遍历元素
        HashMap<String, Integer> visitedMap = new HashMap<>();

        // 构建辅助队列
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord); // 初始化入队
        visitedMap.put(beginWord, 1); // 入队则进行标记（记录遍历状态）

        // 遍历队列
        while (!queue.isEmpty()) {
            // 取出元素
            String curStr = queue.poll();
            int path = visitedMap.get(curStr);
            // 对当前字符串的每个位置都尝试26个字母的替换
            for (int i = 0; i < curStr.length(); i++) {
                char[] curStrArr = curStr.toCharArray(); // 将字符串转为字符数组便于操作
                for (char j = 'a'; j <= 'z'; j++) {
                    curStrArr[i] = j; // 替换对应位置字符
                    // 判断替换后生成的新字符串是否在wordList且没有被遍历过，如果是则加入搜索路径
                    String newStr = String.valueOf(curStrArr); // new String(curStrArr)
                    // 判断当前生成的新字符串是否为结束字符序列，是则说明找到了这个路径，直接返回
                    if (newStr.equals(endWord)) {
                        return path + 1; // 找到匹配路径，直接返回结果
                    }
                    // 继续下一个字符串搜索
                    if (wordList.contains(newStr) && !visitedMap.containsKey(newStr)) { // 构建的新字符串要在指定的字符串序列中，且该字符串没有被遍历过
                        queue.offer(newStr); // 加入搜索路径
                        visitedMap.put(newStr, path + 1); // 标记当前字符串已被遍历
                    }
                }
            }
        }

        // 队列遍历完成，没有找到匹配路径
        return 0;
    }

    public static void main(String[] args) {
        // 1.输入控制
        /*
        Scanner sc = new Scanner(System.in);
        System.out.println("1.输入字符串序列个数");
        int n = sc.nextInt();
        System.out.println("2.输入源字符串和目标字符串，用空格间隔");
        sc.nextLine(); // 此处冗余一层输入，分隔输入操作，避免输入处理错误
        String[] inputStrArr = sc.nextLine().split("\\s+");
        String beginStr = inputStrArr[0], endStr = inputStrArr[1];
        System.out.println("3.按行输入字符串序列");
        List<String> wordList = new ArrayList<>();
        while (n-- > 0) {
            wordList.add(sc.nextLine());
        }
         */
        String beginStr = "abc";
        String endStr = "def";
        List<String> wordList = new ArrayList<>();
        wordList.add("efc");
        wordList.add("dbc");
        wordList.add("ebc");
        wordList.add("dec");
        wordList.add("dfc");
        wordList.add("yhn");

        // 2.bfs搜索
        int res = Solution1.ladderLength(beginStr, endStr, wordList);
        System.out.println("最短转换序列长度" + res);
    }
}