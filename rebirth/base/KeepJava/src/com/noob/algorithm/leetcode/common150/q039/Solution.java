package com.noob.algorithm.leetcode.common150.q039;

import java.util.ArrayList;
import java.util.List;

class Solution {
    
    private List<List<Integer>> ans = new ArrayList<>();
    private List path = new ArrayList<>();
    private int[] candidates;
    private int target;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.candidates = candidates;
        this.target = target;
        dfs(0, 0);
        return ans;
    }

    private void dfs(int start, int sum) {
        if (sum == target) {
            ans.add(new ArrayList<>(path));
            return;
        }
        if (sum > target) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            path.add(candidates[i]);
            dfs(i, sum + candidates[i]);
            path.remove(path.size() - 1);
        }
    }

}