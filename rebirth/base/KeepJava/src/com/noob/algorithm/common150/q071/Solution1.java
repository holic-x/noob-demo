package com.noob.algorithm.common150.q071;

import com.sun.deploy.util.StringUtils;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * 071 简化路径
 */
public class Solution1 {
    /**
     * 对于path，切割后的names只有四种形式：空字符串、一个点.、两个点..、目录名
     * 对于空字符串和一个点无需做处理，但对于两个点和目录名的存储可以考虑通过栈来实现
     * 遇到两个点：需切换上级目录（栈不为空弹出栈顶元素）
     * 遇到目录名：入栈
     */
    public String simplifyPath(String path) {

        // 对路径进行分割，解析数据
        String[] names = path.split("/");
        // 解析分割后的数据，获取到目录信息（借助栈存储元素）
        Deque<String> stack = new ArrayDeque<>(); // 此处使用双端队列存储
        for(String name: names){
            if("".equals(name) || ".".equals(name)){
                // 如果是空字符串（////这种情况下分割出来的）或者`.`则不做处理
                continue;
            }else if("..".equals(name)){
                // 对于两个点，需切换上级目录（栈不为空弹出栈顶元素）
                if(!stack.isEmpty()){
                    stack.pollLast(); // 弹出栈顶元素
                }
            }else{
                stack.offerLast(name); // 对于目录名则压栈
            }
        }

        // 遍历栈中元素（按照栈底-栈顶的顺序拼接路径）
        StringBuffer sb = new StringBuffer();
        // 栈为空则表示根目录，栈不为空则依次拼接信息
        if(stack.isEmpty()){
            sb.append("/");
        }else{
            while(!stack.isEmpty()){
                sb.append("/" + stack.pollFirst());
            }
        }

        // 返回拼接后的结果
        return sb.toString();
    }

}
