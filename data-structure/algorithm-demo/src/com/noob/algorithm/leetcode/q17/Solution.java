package com.noob.algorithm.leetcode.q17;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 17.电话号码的字母组合
 */
public class Solution {

    // 暴力法：
    public List<String> letterCombinations(String digits) {
        // 定义数字对应的字母字典
        String[] letters = new String[]{"","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        // 核心思路：依次遍历数字元素，然后基于此动态追加元素
        List<StringBuffer> res = new ArrayList<>();
        res.add(new StringBuffer());
        for(int i = 0; i < digits.length(); i++){
            int size = res.size(); // 定义子集大小，基于此依次追加元素构建新的子集
            List<StringBuffer> newRes = new ArrayList<>();
            for(int j = 0; j < size; j++){
                int index = Integer.parseInt(String.valueOf(digits.charAt(i)))-1; // 获取数字对应的字母所在索引
                for(int k = 0; k < letters[index].length(); k++){
                    res.get(j).append(letters[index].charAt(k)); // 每个元素子集中追加元素
                    newRes.add(res.get(j));
                }
                // 一次元素遍历完成就用新子集替换老子集
                res = newRes;
            }
        }
        return res.stream().map(sb -> sb.toString()).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        String digits = "23";
        Solution solution = new Solution();
        System.out.println(solution.letterCombinations(digits));

    }
}
