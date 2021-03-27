package com.gokigendori.leetcode.medium;

import com.gokigendori.leetcode.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Medium {

    public int lengthOfLIS(int[] nums) {
        int result = 1;
        int[] dp = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] < nums[j]) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                    result = Math.max(dp[j] + 1, result);
                }
            }
        }
        return result;
    }


    int isLandSize = 0;

    public int maxAreaOfIsland(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                    islandSizeRecursive(grid, i, j);
                }
            }
        }
        return isLandSize;
    }

    public void islandSizeRecursive(int[][] grid, int yy, int xx) {
        int[] x = new int[]{0, 0, -1, 1};
        int[] y = new int[]{1, -1, 0, 0};
        ArrayDeque<Pair> q = new ArrayDeque<>();
        int size = 1;
        isLandSize = Math.max(isLandSize, size);
        q.add(new Pair(yy, xx));
        while (!q.isEmpty()) {
            Pair p = q.poll();
            for (int i = 0; i < 4; i++) {
                if (p.key + y[i] < 0 || grid.length <= p.key + y[i]) {
                    continue;
                }
                if (p.value + x[i] < 0 || grid[0].length <= p.value + x[i]) {
                    continue;
                }
                if (grid[p.key + y[i]][p.value + x[i]] == 1) {
                    grid[p.key + y[i]][p.value + x[i]] = 0;
                    q.add(new Pair(p.key + y[i], p.value + x[i]));
                    size++;
                    isLandSize = Math.max(isLandSize, size);
                }
            }
        }
    }

    public void nextPermutation(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        int pre = -1;
        int pos = -1;
        int min = 1000;
        int minPos = -1;
        for (int i = nums.length - 1; 0 <= i; i--) {
            if (pre > nums[i]) {
                pos = i;
                break;
            }
            pre = nums[i];
        }
        if (pos == -1) {
            reverse(nums);
            return;
        }
        for (int i = pos; i < nums.length; i++) {
            if (nums[pos] < nums[i] && nums[i] < min) {
                min = nums[i];
                minPos = i;
            }
        }
        int tmp = nums[pos];
        nums[pos] = min;
        nums[minPos] = tmp;

        List<Integer> list = new ArrayList<>();
        for (int k = pos + 1; k < nums.length; k++) {
            list.add(nums[k]);
        }
        Collections.sort(list);
        for (int k = 0; k < list.size(); k++) {
            nums[pos + k + 1] = list.get(k);
        }
    }

    public static void reverse(int[] input) {
        List<Integer> list = new ArrayList<>();
        for (int j : input) {
            list.add(j);
        }
        Collections.sort(list);
        for (int i = 0; i < input.length; i++) {
            input[i] = list.get(i);
        }
    }


    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()));
        for (Integer key : map.keySet()) {
            pq.add(new Pair(key, map.get(key)));
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = pq.poll().getKey();
        }
        return result;
    }

    public class Pair {
        int key;
        int value;

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }
    }

    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        for (int i = 2; i < nums.length; i++) {
            if (i == 2) {
                nums[i] += nums[i - 2];
            } else {
                nums[i] += Math.max(nums[i - 2], nums[i - 3]);
            }
        }
        return Math.max(nums[nums.length - 1], nums[nums.length - 2]);
    }

    public int numIslands(char[][] grid) {
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    result++;
                    grid[i][j] = '0';
                    islandRecursive(grid, i, j);
                }
            }
        }
        return result;
    }

    public void islandRecursive(char[][] grid, int yy, int xx) {
        int[] x = new int[]{0, 0, -1, 1};
        int[] y = new int[]{1, -1, 0, 0};
        for (int i = 0; i < 4; i++) {
            if (yy + y[i] < 0 || grid.length <= yy + y[i]) {
                continue;
            }
            if (xx + x[i] < 0 || grid[0].length <= xx + x[i]) {
                continue;
            }
            if (grid[yy + y[i]][xx + x[i]] == '1') {
                grid[yy + y[i]][xx + x[i]] = '0';
                islandRecursive(grid, yy + y[i], xx + x[i]);
            }
        }
    }

    List<List<Integer>> result;

    public List<List<Integer>> permute(int[] nums) {
        result = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        permuteRecursive(nums, set, new ArrayList<>());
        return result;
    }

    public void permuteRecursive(int[] nums, Set<Integer> set, List<Integer> list) {
        if (set.size() == nums.length) {
            result.add(list);
            return;
        }
        for (int i : nums) {
            if (!set.contains(i)) {
                List<Integer> next = new ArrayList<>(list);
                next.add(i);
                HashSet<Integer> clone = new HashSet<>(set);
                clone.add(i);
                permuteRecursive(nums, clone, next);
            }
        }
    }

    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        long abs = Math.abs((long) n);
        double target = x;
        if (n < 0) {
            target = 1 / x;
        }
        double result;
        if (n % 2 == 0) {
            result = Math.pow(target * target, abs / 2);
        } else {
            result = Math.pow(target * target, (abs - 1) / 2);
            result *= target;
        }
        return result;
    }

    /* Get the node with the smallest value greater than this one. */
    private TreeNode getSuccessor(TreeNode node) {
        TreeNode succ = node.right;
        while (succ.left != null && succ.left != node) {
            succ = succ.left;
        }
        return succ;
    }

    public TreeNode convertBST(TreeNode root) {
        int sum = 0;
        TreeNode node = root;

        while (node != null) {
            /*
             * If there is no right subtree, then we can visit this node and
             * continue traversing left.
             */
            if (node.right == null) {
                sum += node.val;
                node.val = sum;
                node = node.left;
            }
            /*
             * If there is a right subtree, then there is at least one node that
             * has a greater value than the current one. therefore, we must
             * traverse that subtree first.
             */
            else {
                TreeNode succ = getSuccessor(node);
                /*
                 * If the left subtree is null, then we have never been here before.
                 */
                if (succ.left == null) {
                    succ.left = node;
                    node = node.right;
                }
                /*
                 * If there is a left subtree, it is a link that we created on a
                 * previous pass, so we should unlink it and visit this node.
                 */
                else {
                    succ.left = null;
                    sum += node.val;
                    node.val = sum;
                    node = node.left;
                }
            }
        }
        return root;
    }

    public TreeNode convertBST2(TreeNode root) {
        if (root == null) {
            return root;
        }
        List<Integer> list = new ArrayList<>();
        recursiveTree(root, list);

        int[] num = new int[list.size()];
        num[0] = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            num[i] = num[i - 1] + list.get(i);
        }
        updateTree(root, num, 0);
        return root;
    }

    public int updateTree(TreeNode node, int[] num, int pos) {

        if (node.right != null) {
            pos = updateTree(node.right, num, pos);
        }
        node.val = num[pos];
        pos++;
        if (node.left != null) {
            pos = updateTree(node.left, num, pos);
        }
        return pos;
    }

    public void recursiveTree(TreeNode node, List<Integer> list) {
        if (node.right != null) {
            recursiveTree(node.right, list);
        }
        list.add(node.val);
        if (node.left != null) {
            recursiveTree(node.left, list);
        }
    }

    public int numTeams(int[] rating) {
        int result = 0;
        for (int i = 1; i < rating.length - 1; i++) {
            int[] less = new int[2];
            int[] more = new int[2];
            for (int j = 0; j < rating.length; j++) {
                if (i == j) {
                    continue;
                }
                if (rating[j] < rating[i]) {
                    if (i < j) {
                        less[0]++;
                    } else {
                        less[1]++;
                    }
                } else {
                    if (i < j) {
                        more[0]++;
                    } else {
                        more[1]++;
                    }
                }
            }
            result += less[1] * more[0] // 増加;
                    + more[1] * less[0]; // 減少
        }
        return result;
    }

}
