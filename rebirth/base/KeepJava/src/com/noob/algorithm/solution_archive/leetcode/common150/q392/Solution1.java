package com.noob.algorithm.solution_archive.leetcode.common150.q392;

/**
 * 判断子序列（392）
 */
public class Solution1 {

    /**
     * 判断s是否为t的子序列：子序列在原字符串中可以不连续，但必须有序
     * s:target 要进行判断的子序列
     * t：source 源序列
     */
    public boolean isSubsequence(String s, String t) {

        // 定义两个指针记录当前遍历的位置
        int spoint = 0;
        int tpoint = 0;

        // 遍历s字符序列，依次校验是否匹配
        while(tpoint<t.length()){
            // 判断spoint是否提前结束
            if(spoint==s.length()){
                break;
            }
            // 校验当前两个指针指向的字符是否一致
            char curS = s.charAt(spoint);
            char curT = t.charAt(tpoint);
            if(curS==curT){
                // 如果匹配，两个指针都往前走，继续遍历下一个位置
                spoint++;
                tpoint++;
            }else{
                // 如果不匹配，则tpoint继续往前走
                tpoint ++;
            }
        }

        // 当t字符序列遍历完成，判断spoint是否走完
        return spoint==s.length();
    }

    public static void main(String[] args) {
        System.out.println(new Solution1().isSubsequence("abc", "ahbgdc"));
    }

}
