package com.gokigendori.leetcode.medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class Find132pattern {
    public boolean find132pattern(int[] nums) {
        int[] min = new int[nums.length];
        min[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min[i] = Math.min(nums[i], min[i - 1]);
        }
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = nums.length - 1; 0 <= i; i--) {
            while (!queue.isEmpty() && queue.peek() < nums[i]) {
                if (min[i] < queue.peek()) {
                    return true;
                }
                queue.pop();
            }
            queue.push(nums[i]);
        }
        return false;
    }

}
