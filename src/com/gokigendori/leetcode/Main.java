package com.gokigendori.leetcode;

public class Main {
    public static void main(String[] args) {
        Easy easy = new Easy();
//        easy.longestWord(new String[]{"yo","ew","fc","zrc","yodn","fcm","qm","qmo","fcmz","z","ewq","yod","ewqz","y"});
//        easy.reverseStr("abcd", 4);
//        easy.imageSmoother(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}});
//        easy.minCostClimbingStairs(new int[]{10, 15, 20});
        TreeNode treeNode = TreeNode.createTree(new Integer[]{6, 2, 8, 0, 4, 7, 9, null, null, 3, 5});
        easy.lowestCommonAncestor(
                treeNode,
                TreeNode.search(treeNode, 2),
                TreeNode.search(treeNode, 8));
    }
}
