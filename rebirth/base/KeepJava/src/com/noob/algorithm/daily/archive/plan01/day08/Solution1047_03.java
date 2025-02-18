package com.noob.algorithm.daily.archive.plan01.day08;

import java.util.HashSet;
import java.util.Set;

/**
 * 🟢 1047 删除字符串中的所有相邻重复项
 */
public class Solution1047_03 {

    // 替换法（规律法）：和【有效的括号】思路类似，定义重复的序列，通过将这些重复的序列不断替换得到结果 ❌❌❌ 超时
    public String removeDuplicates(String s) {
        Set<String> set = new HashSet<>();
        set.add("aa");
        set.add("bb");
        set.add("cc");
        set.add("dd");
        set.add("ee");
        set.add("ff");
        set.add("gg");
        set.add("hh");
        set.add("ii");
        set.add("jj");
        set.add("kk");
        set.add("ll");
        set.add("mm");
        set.add("nn");
        set.add("oo");
        set.add("pp");
        set.add("qq");
        set.add("rr");
        set.add("ss");
        set.add("tt");
        set.add("uu");
        set.add("vv");
        set.add("ww");
        set.add("xx");
        set.add("yy");
        set.add("zz");


        while(validRepeat(s,set)){ // 此处每次都要从set中进行全校验，一些重复字符串可能会重复出现，不能单纯用for控制
            // 找到重复的字符串进行替换
            for(String repeatStr : set){
                s = s.replace(repeatStr,"");
            }
        }

        // 返回结果
        return s;
    }

    /**
     * 校验出现的重复字符串
     */
    public boolean validRepeat(String s ,Set<String> set){
        for (String repeatStr : set) {
            if(s.contains(repeatStr)){
                return true;
            }
        }
        // 没有出现重复则返回-1标识
        return false;
    }
}
