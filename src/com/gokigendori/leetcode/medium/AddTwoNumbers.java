package com.gokigendori.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        List<Integer> li1 = new ArrayList<>();
        List<Integer> li2 = new ArrayList<>();
        ListNode ll = l1;
        while (ll != null) {
            li1.add(ll.val);
            ll = ll.next;
        }

        ll = l2;
        while (ll != null) {
            li2.add(ll.val);
            ll = ll.next;
        }

        int limit = Math.max(li1.size(), li2.size());
        List<Integer> ret = new ArrayList<>();
        int countUp = 0;
        for (int i = 0; i < limit; i++) {
            int sum = countUp;
            if (i < li1.size()) {
                sum += li1.get(i);
            }
            if (i < li2.size()) {
                sum += li2.get(i);
            }
            ret.add(sum % 10);
            countUp = sum / 10;
        }

        if (0 < countUp) {
            ret.add(countUp);
        }

        ListNode out = new ListNode();
        ListNode tmp = out;
        for (int i = 0; i < ret.size(); i++) {
            tmp.val = ret.get(i);
            if (i != ret.size() - 1) {
                tmp.next = new ListNode();
                tmp = tmp.next;
            }
        }
        return out;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


}
