package com.noob.algorithm.dmsxl.leetcode.q925;

import java.util.HashSet;
import java.util.Set;

/**
 * 🟢925 长按键入
 */
public class Solution2 {

    // 双指针遍历思路
    public boolean isLongPressedName(String name, String typed) {
        int nLen = name.length(), tLen = typed.length();
        // 分别定义指针用于遍历两个字符串
        int nIdx = 0, tIdx = 0;
        while (nIdx < nLen && tIdx < tLen) {
            // 校验当前遍历元素是否匹配
            char curN = name.charAt(nIdx);
            char curT = typed.charAt(tIdx);
            if (curN == curT) {
                // 当前遍历元素匹配，两者继续向前移动
                nIdx++;
                tIdx++;
            } else {
                // 当前遍历元素不匹配则分情况讨论
                // ① 如果第一个元素就不匹配则直接返回false
                if (nIdx == 0) {
                    return false;
                }
                // ② 如果非第一个元素，则考虑是出现了重复键入的元素，typed需要跳过这些重复键入的元素
                while (tIdx < tLen - 1 && typed.charAt(tIdx) == typed.charAt(tIdx - 1)) { // 注意数组越界处理
                    tIdx++;
                }
                // 当tIdx跨越了重复键入的元素之后，再次继续比较，如果此时还是不匹配则说明不满足
                if (typed.charAt(tIdx) != curN) {
                    return false;
                } else {
                    // 如果匹配，则两个指针继续向前移动
                    nIdx++;
                    tIdx++;
                }
            }
        }

        // 校验是否还有剩余元素没匹配完成
        // ① 如果是name剩余，则说明typed字符无法完全匹配name
        if (nIdx < nLen) {
            return false;
        }
        // ② 如果是typed剩余，则校验是否是出现了多余的尾巴导致剩余（同理，跳过重复元素即可）
        while (tIdx < tLen && typed.charAt(tIdx) == typed.charAt(tIdx - 1)) {
            tIdx++;
        }
        // 判断tIdx可以顺利遍历到末尾,如果可以说明剩余的都是重复的尾巴
        return tIdx == tLen;
    }
}
