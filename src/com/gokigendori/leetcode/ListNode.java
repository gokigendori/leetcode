package com.gokigendori.leetcode;

public class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public static ListNode createNoe(Integer[] array) {
        ListNode p = new ListNode(array[0]);
        ListNode pp = p;
        for (int i = 1; i < array.length; i++) {
            pp.next = new ListNode(array[i]);
            pp = pp.next;
        }
        return p;
    }
}
