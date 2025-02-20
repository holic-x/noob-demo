package com.noob.algorithm.solution_archive.leetcode.common150.q014;

/**
 * 14 最长公共前缀
 */
public class Solution1 {

    /**
     * 思路：理想情况下最长前缀是这个数组中长度最小的字符串，因此可以拆分思路：
     * 1.找到这个理想的最长前缀 prefix(长度最小的字符串)
     * 2.通过截取这个prefix来依次进行匹配，确认最长
     */
    public String longestCommonPrefix(String[] strs) {
        // 定义最大前缀
        String maxPrefix = "";

        String minStr = strs[0];
        // 1.找到minStr(最短ed字符串)
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() < minStr.length()) {
                minStr = strs[i];
            }
        }

        // 预设maxPrefix为理想情况
        maxPrefix = minStr;

        // 2.通过截取minStr，分别和数组元素进行匹配（此处需注意i最大可以取到minStr.length()位置）
        for(int i = 0; i <= minStr.length(); i++) {
            String prefix = minStr.substring(0, i);
            // 如果校验不匹配则直接返回
            if(!valid(prefix,strs)){
                return minStr.substring(0, i-1);
            }
        }

        return maxPrefix; // return null; ""表示不存在最长前缀
    }

    // 校验数组中的每个元素是否有公共前缀
    public boolean valid(String prxfix,String[] strs) {
        for (String str : strs) {
            if(!str.startsWith(prxfix)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution1 solution = new Solution1();
        String[] strs = new String[]{"a","b"};
        solution.longestCommonPrefix(strs);
    }

}
