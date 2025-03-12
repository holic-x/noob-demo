package com.noob.algorithm.daily.codeTop;


/**
 * 🟡 423 从英文中重建数字 - https://leetcode.cn/problems/reconstruct-original-digits-from-english/
 */
public class Solution423_01 {

    // 字符串 s ，其中包含字母顺序打乱的用英文单词表示的若干数字（0-9）。按 升序 返回原始的数字
    public String originalDigits(String s) {
        // 定义数组存储每个数字的出现次数
        int[] digits = new int[10]; // 下标代表数字、元素值代表该数字出现次数
        // 定义字符串中每个字母的出现次数
        int[] letters = new int[128]; // 下标表示字母，元素值表示该字母的出现次数

        // 遍历字符串，统计字母出现次数
        for (char ch : s.toCharArray()) {
            letters[ch]++;
        }

        // ① 字母只在1个数字中出现的情况（字母出现次数即为数字出现次数）
        // g、u、w、x、z 分别对应 8 4 2 6 0
        digits[8] = letters['g'];
        digits[4] = letters['u'];
        digits[2] = letters['w'];
        digits[6] = letters['x'];
        digits[0] = letters['z'];

        // ② 关注字母在2个数字中出现的情况，根据已有的数字出现次数推导另一个数字的出现次数
        // 【f 45】  【h 38】  【s 67】  【v 57】
        digits[5] = letters['f'] - digits[4];
        digits[3] = letters['h'] - digits[8];
        digits[7] = letters['s'] - digits[6];

        // ③ 目前剩下 1 9 还未推导
        // 1在e、n、o中出现，但只有o才能推导（因为e、n中9的出现次数还没确定）【o  0124】
        digits[1] = letters['o'] - digits[0] - digits[2] - digits[4];
        // 9在e、i、n中出现，需注意3中有2个e、9中有2个n，此处选用i处理【i 5689】
        digits[9] = letters['i'] - digits[5] - digits[6] - digits[8]; // 【i 5689】
//        digits[9] = letters['i'] - digits[0] - digits[1] - 2*digits[3] - digits[5] - digits[8]; // 【e 013589】 其中3有2个e
//        digits[9] = (letters['i'] - digits[1] - digits[7]) / 2; // 【n 179】 其中9有2个n

        // 基于上述统计的数字出现情况，拼接字符串
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < digits.length; i++) {
            int cnt = digits[i];
            while (cnt-- > 0) {
                buffer.append(i);
            }
        }
        // 返回结果
        return buffer.toString();
    }

}
