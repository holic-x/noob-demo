package com.noob.algorithm.solution_archive.dmsxl.leetcode.q020;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 020 有效的括号
 */
public class Solution2 {

    // 栈：左括号入栈，碰到匹配的右括号则弹出（之所以选用栈是因为要优先匹配相近的左括号，因此后入的左括号优先与正向遍历的右括号进行匹配）
    public boolean isValid(String s) {
        // 定义括号字符匹配集合
        Map<Character,Character> map = new HashMap<>();
        map.put('}','{');
        map.put(')','(');
        map.put(']','[');

        // 定义辅助栈
        Stack<Character> stack = new Stack<>();

        // 遍历目标字符串的字符序列，校验字符
        for(char cur : s.toCharArray()){
            if(map.containsValue(cur)){
                // 如果是左括号则入栈（等待和右括号匹配）
                stack.push(cur);
            }else if(map.containsKey(cur)){
                // 如果是右括号则从当前栈中弹出元素校验是否匹配（如果匹配则继续下一个位置判断，如果不匹配则直接返回false）
                if(stack.isEmpty()){
                    return false; // 存在右括号，但是此时栈为空（说明没有匹配的左括号）
                }else{
                    Character top = stack.pop();
                    if(!map.get(cur).equals(top)){
                        return false;
                    }
                }
            }
        }
        // 遍历结束，最终栈为空则说明完全匹配
        return stack.isEmpty();
    }

}
