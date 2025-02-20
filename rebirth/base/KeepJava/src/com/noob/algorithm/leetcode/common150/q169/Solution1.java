package com.noob.algorithm.leetcode.common150.q169;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * 169 多数元素
 * 多数元素指在数组中出现次数大于n/2的元素
 */
public class Solution1 {

    // 计数法
    public int majorityElement1(int[] nums) {
        // 思路：定义集合进行计数，判断元素出现次数大于n/2（只有1个）
        HashMap<Integer, Integer> map = new HashMap<>(); // map<元素,对应出现次数>
        for(int i=0;i<nums.length;i++){
            map.put(nums[i],map.getOrDefault(nums[i],0)+1); // getOrDefault：指定key存在则返回value，不存在则返回0
        }
        // 遍历map集合，判断元素出现此处是否大于n/2
        Set<Integer> set = map.keySet();
        Iterator<Integer> iterator = set.iterator();
        while(iterator.hasNext()){
            int key = iterator.next();
            if(map.get(key)>nums.length/2){
                return key;
            }
        }
        // 其他情况返回默认值
        return -1;
    }

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        System.out.println(solution1.majorityElement1(new int[]{3,2,3}));
    }
}
