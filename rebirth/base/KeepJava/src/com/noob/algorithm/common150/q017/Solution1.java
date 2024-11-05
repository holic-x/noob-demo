package com.noob.algorithm.common150.q017;

import java.util.*;

/**
 * 17 电话号码的字母组合
 */
public class Solution1 {

    // 队列法：遍历每个数字，将每次遍历拼接后的结果存入队列，每次遍历的时候从队列中取出所有元素进行拼接然后重新放入队列
    public List<String> letterCombinations(String digits) {
        Map<Integer, String> map = new HashMap<>();
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");

        // 空字符串验证
        if (digits == null || "".equals(digits)) {
            return new ArrayList<>();
        }

        // 定义队列存储字符串的变化过程
        List<String> list = new ArrayList<>();
        list.add("");// 初始化一个空字符串入队

        // 遍历数组元素，获取到每个数字，然后组合拼接
        for (int i = 0; i < digits.length(); i++) {
            // 获取到对应数字及其字母映射
            int idx = digits.charAt(i) - '0';
            String str = map.get(idx);
            // 遍历映射的字符串，从队列中依次取出元素进行拼接，随后重新装入队列，等待下次拼接
            int listSize = list.size(); // 记录当前list集合大小（后续需要将组装的string加入队尾，此处只遍历到目前的队列大小为止）
            for (int j = 0; j < listSize; j++) {
                // 取出队首元素
                String findFirst = list.remove(0);
                // 分别拼接字符，然后入队
                for (int k = 0; k < str.length(); k++) {
                    list.add(findFirst + str.charAt(k));
                }
            }
        }

        // 返回最终结果
        return list;
    }

}
