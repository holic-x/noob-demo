package com.noob.algorithm.solution_archive.leetcode.common150.q030;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 30 串联所有单词的子串
 */
public class Solution1 {

    /**
     * 分析：滑动窗口
     * 外层循环：定义一个固定的滑动窗口（窗口大小：单词个数*单词长度，因为单词是等长的）
     * 内存循环：目的是为了判断每个滑动窗口的数据是否为串联子串
     * - 定义一个集合存储单词（可重复集合）（因为此处每次判断都会移出集合元素，因此在循环中重建、移出）
     * - 将滑动窗口中的子串拆分为n（n为单词个数）部分，判断每个部分是否存在于集合中，存在则移除，不存在则说明不符合子串要求（直到所有子串判断完成）
     * 此处这个List也可用Map替代：key存储单词元素，value存储出现次数（当subStr不在这个Map中或者key对应value为0则说明不匹配）
     */
    public List<Integer> findSubstring(String s, String[] words) {
        // 定义结果接
        List<Integer> res = new ArrayList<>();

        // 计算滑动窗口大小
        int len = words[0].length() * words.length;
        // 定义一个滑动窗口（起始指针）
        int start = 0, end = len;

        // 外层循环(因为窗口有固定长度，此处只需要遍历到sLen-len的位置即可)
        for (int i = 0; i < s.length() - len; i++) {
            String target = s.substring(start, end);
            // 判断target是否满足串联子串要求,满足将当前索引位置记录下来
            if(isValid(target, words)) {
                res.add(start);
            }
            // 本次遍历判断结束，指针继续向前
            start++;
            end++;
        }

        // 返回结果
        return res;
    }

    public boolean isValid(String target, String[] words) {
        // 1.将words放到一个集合中便于处理
        List<String> list = Arrays.asList(words);

        // 2.将target拆分为words数组大小的n个部分
        int singleWordLen = words[0].length();
        int wordsLen = words.length;
        List<String> targetList = new ArrayList<>(wordsLen);
        for (int i = 0; i < wordsLen; i = i + singleWordLen) {
            targetList.add(target.substring(i, i + singleWordLen)); // 每次截取单词长度的大小，截取完成i继续前进（步长为单词长度大小）
        }

        // 3.循环遍历目标集，校验是否匹配 // todo 思路正常，但实现存在问题（不能在判断list的时候移出元素）
        for(String str : targetList) {
            // 判断str是否存在于list中，存在则移除
            if(list.contains(str)) {
                list.remove(str);
            }else{
                // 不存在则说明不符合子串要求
                return false;
            }
        }
        return true;
    }

}
