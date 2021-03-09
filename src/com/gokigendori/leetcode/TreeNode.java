package com.gokigendori.leetcode;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode(int x) {
        val = x;
    }

    public static TreeNode createNode(Integer[] array) {
        TreeNode p = new TreeNode(array[0]);
        recursive(p, array, 0);
        return p;
    }

    public static void recursive(TreeNode treeNode, Integer[] array, int pos) {
        if (array.length <= pos * 2) {
            return;
        }
        if (pos * 2 + 1 < array.length && array[pos * 2 + 1] != null) {
            treeNode.left = new TreeNode(array[pos * 2 + 1]);
            recursive(treeNode.left, array, pos * 2 + 1);
        }
        if (pos * 2 + 2 < array.length && array[pos * 2 + 2] != null) {
            treeNode.right = new TreeNode(array[pos * 2 + 2]);
            recursive(treeNode.right, array, pos * 2 + 2);
        }
    }

    public static TreeNode search(TreeNode tree, int target) {
        if (tree.val == target) {
            return tree;
        }
        TreeNode result = null;
        if (tree.left != null) {
            result = search(tree.left, target);
        }
        if (tree.right != null && result == null) {
            result = search(tree.right, target);
        }
        return result;
    }
}
