package com.gokigendori.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {

        List<Integer> list = new ArrayList<>();
        for (int i : nums) {
            list.add(i);
        }
        Collections.sort(list);

        List<List<Integer>> answer = new ArrayList<>();
        for (int i = 0; i < list.size() - 2; i++) {
            if (0 < i && list.get(i).equals(list.get(i - 1))) {
                continue;
            }
            twoSum(list, i, answer);
        }
        return answer;
    }

    private void twoSum(List<Integer> list, int i, List<List<Integer>> answer) {
        int left = i + 1;
        int right = list.size() - 1;

        int one = list.get(i);

        while (left < right) {
            Integer two = list.get(left);
            Integer three = list.get(right);

            int total = one + two + three;
            if (total == 0) {
                ArrayList<Integer> ans = new ArrayList<>();
                ans.add(one);
                ans.add(two);
                ans.add(three);

                answer.add(ans);
                while (left < right && list.get(left).equals(list.get(left + 1))) {
                    left++;
                }
                while (left < right && list.get(right).equals(list.get(right - 1))) {
                    right--;
                }
                right--;

            } else if (total < 0) {
                left++;
            } else {
                // 0 < total
                right--;
            }
        }
    }
}
