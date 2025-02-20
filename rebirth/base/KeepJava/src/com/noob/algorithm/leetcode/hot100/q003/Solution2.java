package com.noob.algorithm.leetcode.hot100.q003;

import java.util.HashSet;

/**
 * 3 无重复最长子串
 */
public class Solution2 {

    /**
     * 滑动窗口思路：
     * 1.初始化滑动窗口[left，right]进行移动
     * 2.依次循环遍历元素（当指针走到字符尾部说明遍历结束），判断元素是否已在窗口中
     * - 如果元素不存在窗口中则将元素加入窗口，并执行right++，更新max（滑动窗口的大小对照的就是子串的长度）
     * - 如果元素存在于窗口中则需要移除重复元素，并执行left++
     */
    public int lengthOfLongestSubstring(String s) {
        // 定义子串最大长度
        int max = 0;
        // 定义滑动窗口指针
        int left = 0,right=0;
        // 定义滑动窗口（此处使用哈希表存储）
        HashSet<Character> set = new HashSet<Character>();

        // 循环条件：当左右边界都到达字符串s最后一个元素，则循环结束
        int len = s.length();
        while(left < len && right < len){
            // 判断窗口中是否包含right指向的元素
            if(!set.contains(s.charAt(right))){
                // 如果不包含则添加元素到窗口中，并移动right指针，同步更新max
                set.add(s.charAt(right));
                right ++; // right 向前
                max = Math.max(max, set.size());
            }else{
                // 如果包含则需移除重复元素（此处只考虑窗口元素个数/内容，不考虑有序性）
                set.remove(s.charAt(left));
                left ++; // left 指针向前
            }
        }
        // 返回结果
        return max;
    }
}
