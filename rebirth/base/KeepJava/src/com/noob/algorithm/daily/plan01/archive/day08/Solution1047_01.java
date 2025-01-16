package com.noob.algorithm.daily.plan01.archive.day08;

import java.util.Stack;

/**
 * 🟢 1047 删除字符串中的所有相邻重复项
 */
public class Solution1047_01 {

    // 辅助栈：构建辅助栈存储已遍历元素（出现重复则剔除）
    public String removeDuplicates(String s) {
        // 构建辅助栈存储遍历元素
        Stack<Character> stack = new Stack<>();
        for(char cur : s.toCharArray()){
            /**
             * 栈不为空时，比较栈顶元素与当前遍历元素
             * - 如果相同说明出现了重复元素则弹出栈顶元素(此处需要消除重复的元素，因此重复元素不需要再次入栈)
             * - 否则将cur元素入栈等待校验
             */
            if(!stack.isEmpty() && stack.peek()==cur){
                stack.pop();
            }else{
                stack.push(cur);
            }
        }

        // 遍历完成，栈中留存的元素即为最终的字符串序列，需依次弹出并逆序输出
        String res = new String();
        while(!stack.isEmpty()){
            res = stack.pop() + res;
        }
        // 返回结果
        return res;
    }
}
