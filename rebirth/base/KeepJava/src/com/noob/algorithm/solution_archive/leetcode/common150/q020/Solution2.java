package com.noob.algorithm.solution_archive.leetcode.common150.q020;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution2 {

    /**
     * 栈思路：利用栈先进后出的特点
     * 1.构建括号映射Map<k,v> k 为右括号，v为左括号
     * 2.左括号入栈，遇到右括号则出栈进行比较
     */
    public boolean isValid(String s) {
        // 构建括号映射
        Map<Character,Character> map = new HashMap<>();
        map.put(')','(');
        map.put('}','{');
        map.put(']','[');

        // 定义栈存储左括号序列
        Stack<Character> stack = new Stack<>();
        // 遍历字符串序列，如果遇到左括号则入栈，右括号则弹出栈顶元素进行比较
        for(int i=0;i<s.length();i++){
            char cur = s.charAt(i);
            if(map.containsValue(cur)){
                // 遇到左括号则入栈
                stack.push(cur);
            }else if(map.containsKey(cur)){
                // 遇到右括号，则弹出栈顶元素进行比较，确认括号是否一一匹配
                if(stack.isEmpty()||stack.peek()!=map.get(cur)){ // 如果栈为空，或者括号不匹配则为无效的括号，直接返回false
                    return false;
                }else{
                    // 弹出元素，继续下一轮比较
                    stack.pop(); // pop 方法移除并返回栈顶元素
                }
            }
        }
        // 校验最终stack中的元素，如果还存在元素则说明不匹配
        return stack.isEmpty();
    }
}
