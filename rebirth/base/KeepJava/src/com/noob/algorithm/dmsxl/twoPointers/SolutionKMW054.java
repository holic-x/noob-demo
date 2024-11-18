package com.noob.algorithm.dmsxl.twoPointers;

import java.util.Arrays;

/**
 * 卡码网 054 替换数字
 */
public class SolutionKMW054 {

    /**
     * 将target中的数字置换为"number"：
     * （1）统计字符串中数字的个数cnt
     * （2）根据统计个数cnt扩容数组，通过双指针从后往前置换
     * - pointer：用于遍历元素；- cur：用于指向当前填充的位置
     */
    public String convertDigit(String target){
        // 1.统计字符串中数字的个数
        int cnt = 0; // 数字个数统计
        char[] targetArr = target.toCharArray();
        for(char ch : targetArr){
            if(Character.isDigit(ch)){
                cnt++;
            }
        }

        // 2.定义扩容数组，将源字符串数组copy进去
        char[] newArr = Arrays.copyOfRange(targetArr, 0,target.length() + 5 * cnt);
        // 遍历扩容后的新数组：pointer（从后往前遍历元素）、cur（从后往前填充元素）
        int cur = newArr.length -1;
        for(int pointer=target.length()-1;pointer>=0;pointer--){
            // 判断当前遍历元素是否为字母，如果为字母则直接填充
            char curCh = newArr[pointer];
            if(Character.isLetter(curCh)){
                newArr[cur--] = curCh;
            }else if(Character.isDigit(curCh)){
                // 如果为数字，则需逆序置换rebmun
                newArr[cur--] = 'r';
                newArr[cur--] = 'e';
                newArr[cur--] = 'b';
                newArr[cur--] = 'm';
                newArr[cur--] = 'u';
                newArr[cur--] = 'n';
            }
        }

        // 3.返回构建好的字符串
        return new String(newArr);
    }

    public static void main(String[] args) {
        String target = "a1b2c3";
        SolutionKMW054 solutionKMW054 = new SolutionKMW054();
        System.out.println(solutionKMW054.convertDigit(target));
    }

}
