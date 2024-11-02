package com.noob.algorithm.common150.q137;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 137 只出现一次的数字II
 */
public class Solution1 {
    public int singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int numSum = 0;
        for (int num : nums) {
            numSum += num;
            set.add(num);
        }

        int setSum = 0;
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            setSum += iterator.next();
        }

        return (setSum * 3 - numSum) / 2;
    }
}
