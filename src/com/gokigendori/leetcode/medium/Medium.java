package com.gokigendori.leetcode.medium;

import com.gokigendori.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Medium {

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
        updateTree(root, num,0);
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
