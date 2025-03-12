package com.noob.algorithm.daily.codeTop;

import java.util.ArrayList;
import java.util.List;

/**
 * 🔴 068 文本左右对齐 - https://leetcode.cn/problems/text-justification/description/
 */
public class Solution068_01 {

    /**
     * 思路分析：
     * ① 构建每一行可放置的最大单词数和所需空格
     * ② 区分最后一行：如果是最后一行则需要进行左对齐操作
     * ③ 构建结果：基于上述操作处理结果
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        int n = words.length;
        // 定义结果集
        List<String> res = new ArrayList<>();

        // 遍历单词列表
        int idx = 0; // 定义遍历words[]数组的指针（用于灵活内部控制指针移动）
        while (idx < n) {
            // ① 确定可放置的单词
            int curLen = 0; // 当前行的字符长度
            int start = idx; // 当前行的起始单词索引
            // 贪心思路：尽可能多放单词
            while (idx < n && curLen + words[idx].length() <= maxWidth) {
                // 如果idx在限定范围内，则尝试校验拼接后的字符长度是否超出maxWidth，如果没有超出则允许放置，如果超出说明当前行已经足够多了
                curLen += words[idx].length() + 1; // 加上当前单词长度，并且+1表示单词间的空格
                idx++; // 指针移动，继续指向下一个位置
            }

            // ② 处理行（区分最后一行）
            int wordCnt = idx - start; // 记录当前行放置的单词个数
            int totalSpaces = maxWidth - (curLen - wordCnt); // 记录总空格数（maxWidth-(当前行字符长度-单词个数（因为上面每加入一个单词就会引入一个空格,所以要从总字符长度中扣除）)）

            // 如果是最后一行或者只有一个单词，则需进行左对齐
            if (idx == n || wordCnt == 1) {
                // 构建范围[start,idx)范围内的单词，进行左对齐
                StringBuffer line = new StringBuffer();
                // 处理单词
                for (int i = start; i < idx; i++) {
                    line.append(words[i]);
                    if (i != idx - 1) { // i<idx-1
                        line.append(" "); // 单词之间补充空格，如果是本行的最后一个单词则暂不补充，后面根据maxWidth填充
                    }
                }
                // 补充剩余空格
                while (line.length() < maxWidth) {
                    line.append(" ");
                }
                // 本行构建完成，处理结果
                res.add(line.toString());
            } else {
                // 如果不是最后一行，则需要均匀地分配空格（基于总的空格数与单词数来计算）
                int spacePerGap = totalSpaces / (wordCnt - 1); // 每个间隔的基础空格数（平均每加入一个单词需要补充多少空格做间隔）
                int extraSpaces = totalSpaces % (wordCnt - 1); // 需要额外分配的空格数（根据加入的单词来决定，前面的单词先额外分配，每加入1个单词则如果存在待额外分配的空格数则分配1个）
                // 同理，构建行
                StringBuffer line = new StringBuffer();
                for (int i = start; i < idx; i++) {
                    line.append(words[i]);
                    if (i != idx - 1) { // i<idx-1
                        // 添加基础空格
                        for (int j = 0; j < spacePerGap; j++) {
                            line.append(" ");
                        }
                        // 添加额外空格（如果存在额外的空格数，则分配1个）
                        if (extraSpaces > 0) {
                            line.append(" ");
                            extraSpaces--;
                        }
                    }
                }
                // 本行构建完成
                res.add(line.toString());
            }
        }
        // 返回结果
        return res;
    }

}
