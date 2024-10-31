package com.noob.algorithm.common150.q125;

/**
 * 125 验证回文串
 */
public class Solution2 {
    /**
     * 需要先对字符串进行处理，最终只保留字母内容并转化为小写，随后判断回文
     */
    public boolean isPalindrome(String s) {

        // 接收处理后的字符串
        StringBuffer str = new StringBuffer();

        // 处理字符串，只保留字母并转化为小写
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(Character.isLetterOrDigit(ch)) { // 'A' <= ch && ch <= 'Z' || 'a' <= ch && ch <= 'z' 此处设定是字母或数字才能通过测试用例
                str.append(Character.toLowerCase(ch));
            }
        }

        // 对处理后字符串大小进行判断
        if(str.length()<=1){
            return true;
        }

        // 通过双指针判断回文
        int left = 0;
        int right = str.length() - 1;
        while(left < right) {
            // 判断左侧和右侧字母是否一致，直到左右指针相遇结束
            if(str.charAt(left) != str.charAt(right)) {
                return false;
            }
            // 如果一致则继续判断（指针相互靠拢）
            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        String s1 = "0P";
        System.out.println(new Solution2().isPalindrome(s1));

    }
}
