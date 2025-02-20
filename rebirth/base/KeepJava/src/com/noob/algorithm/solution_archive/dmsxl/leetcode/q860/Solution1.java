package com.noob.algorithm.solution_archive.dmsxl.leetcode.q860;

/**
 * 860 柠檬水找零
 */
public class Solution1 {

    public boolean lemonadeChange(int[] bills) {
        // 初始化5 10 的零钱个数
        int fiveCnt = 0, tenCnt = 0;
        // 遍历账单信息判断是否足够找零
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 5) {
                // 客户给了5,直接接收
                fiveCnt++;
            } else if (bills[i] == 10) {
                // 客户给了10，判断是否可以找零
                if (fiveCnt < 1) {
                    return false; // 5的张数不足，没法找零
                }
                // 满足找零条件，则更新余额
                tenCnt++;
                fiveCnt--;
            } else if (bills[i] == 20) {
                /**
                 * 贪心思路：此处20的找零方案有2种，一种是5+10，一种是3*5
                 * 此处应该尽可能保留5的纸币才能应对更多可能，因此此处优先选择5+10的方案处理，备选3*5
                 */
                // 客户给了10，判断是否可以找零
                if (fiveCnt >= 1 && tenCnt >= 1) { // 5和10至少有1张
                    // 满足找零条件，更新余额（选择方案）
                    fiveCnt--;
                    tenCnt--;
                } else if (fiveCnt >= 3) { // 5至少有3张
                    fiveCnt = fiveCnt - 3;
                } else {
                    return false; // 两种组合都不符合：5和10的张数都少于1，或者5的张数少于3
                }
            }

        }
        // 账单遍历完成
        return true;
    }
}
