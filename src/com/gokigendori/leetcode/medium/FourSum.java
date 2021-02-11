package com.gokigendori.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FourSum {

    public List<List<Integer>> fourSum(int[] nums, int target) {

        List<Integer> list = new ArrayList<>();
        for (int i : nums) {
            list.add(i);
        }
        Collections.sort(list);

        List<List<Integer>> answer = new ArrayList<>();
        for (int i = 0; i < list.size() - 3; i++) {
            if (0 < i && list.get(i).equals(list.get(i - 1))) {
                continue;
            }
            for (int j = i + 1; j < list.size() - 2; j++) {
                if (i + 1 < j && list.get(j).equals(list.get(j - 1))) {
                    continue;
                }
                twoSum(list, i, j, target, answer);
            }

        }
        return answer;
    }

    private void twoSum(List<Integer> list, int i, int j, int target, List<List<Integer>> answer) {
        int left = j + 1;
        int right = list.size() - 1;

        int one = list.get(i);
        int oneone = list.get(j);

        while (left < right) {
            Integer two = list.get(left);
            Integer three = list.get(right);

            int total = one + oneone + two + three - target;
            if (total == 0) {
                ArrayList<Integer> ans = new ArrayList<>();
                ans.add(one);
                ans.add(oneone);
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
