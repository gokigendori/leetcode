package com.gokigendori.leetcode.medium;

public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            long total = 0;
            for (int j = i; j < nums.length; j++) {
                total += nums[j];
                if (total == k) {
                    ans++;
                }
            }
        }
        return ans;
    }

}
