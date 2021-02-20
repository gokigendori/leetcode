package com.gokigendori.leetcode;

import com.gokigendori.leetcode.medium.Medium;

public class Main {
    public static void main(String[] args) {
        Easy easy = new Easy();
        Medium medium = new Medium();
//        easy.longestWord(new String[]{"yo","ew","fc","zrc","yodn","fcm","qm","qmo","fcmz","z","ewq","yod","ewqz","y"});
//        TreeNode treeNode = TreeNode.createTree(new Integer[]{6, 2, 8, 0, 4, 7, 9, null, null, 3, 5});
//        easy.lowestCommonAncestor(
//                treeNode,
//                TreeNode.search(treeNode, 2),
//                TreeNode.search(treeNode, 8));
        easy.moveZeroes(new int[]{0,1,0,3,12});
//        medium.numTeams(new int[]{1,2,3,4});
    }
}