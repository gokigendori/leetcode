package com.gokigendori.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

public class Easy {
    public String truncateSentence(String s, int k) {
        char[] c = s.toCharArray();
        String result = s;
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ' ') {
                k--;
            }
            if (k == 1) {
                result = s.substring(0, i + 1);
            }
        }
        return result;
    }

    public int arraySign(int[] nums) {
        int minus = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                return 0;
            }
            if (nums[i] < 0) {
                minus++;
            }
        }

        return (minus % 2 == 0) ? 1 : -1;
    }

    public String removeOuterParentheses(String S) {
        char[] c = S.toCharArray();
        int depth = 0;
        StringBuilder sb = new StringBuilder();
        int from = 0;
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '(') {
                depth++;
                if (depth == 1) {
                    from = i;
                }
            } else {
                depth--;
            }
            if (depth == 0) {
                sb.append(S.substring(from + 1, i));
            }
        }
        return sb.toString();
    }

    public boolean isMonotonic(int[] A) {
        Boolean isUpper = null;
        if (A.length == 1) {
            return true;
        }

        for (int i = 1; i < A.length; i++) {
            if (isUpper == null) {
                if (A[i - 1] < A[i]) {
                    isUpper = Boolean.TRUE;
                }
                if (A[i] < A[i - 1]) {
                    isUpper = Boolean.FALSE;
                }
            }
            if (isUpper == Boolean.TRUE) {
                if (!(A[i - 1] <= A[i])) {
                    return false;
                }
            } else if (isUpper == Boolean.FALSE) {
                if (!(A[i] <= A[i - 1])) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean hasAlternatingBits(int n) {
        boolean check = (n & 1) == 1;
        while (n > 0) {
            boolean tmp = (n >> 1 & 1) == 1;
            if (check == tmp) {
                return false;
            }
            n = n >> 1;
            check = tmp;
        }
        return true;
    }

    public int maxAscendingSum(int[] nums) {
        int max = nums[0];
        int pre = nums[0];
        int tmp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= pre) {
                tmp = nums[i];
            } else {
                tmp += nums[i];
            }
            max = Math.max(max, tmp);
            pre = nums[i];
        }
        return max;
    }

    public int[] sortByBits(int[] arr) {
        Arrays.sort(arr);
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i : arr) {
            int num = bitNum(i);
            List<Integer> list = map.getOrDefault(num, new ArrayList<>());
            list.add(i);
            map.put(num, list);
        }
        List<Integer> keys = new ArrayList<>(map.keySet());
        keys.sort(Comparator.naturalOrder());
        List<Integer> result = new ArrayList<>();
        for (Integer key : keys) {
            result.addAll(map.get(key));
        }
        for (int i = 0; i < result.size(); i++) {
            arr[i] = result.get(i);
        }
        return arr;
    }

    public int bitNum(int i) {
        int num = 0;
        while (0 < i) {
            if ((i & 1) == 1) {
                num++;
            }
            i = i >> 1;
        }
        return num;
    }

    public int sumOfUnique(int[] nums) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> deleted = new HashSet<>();
        for (int i : nums) {
            if (set.contains(i)) {
                deleted.add(i);
            } else {
                set.add(i);
            }
        }
        int result = 0;
        for (int i : set) {
            if (!deleted.contains(i)) {
                result += i;
            }
        }
        return result;
    }


    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        int center = nums.length / 2;

        TreeNode tree = new TreeNode(nums[center]);
        if (nums.length == 1) {
            return tree;
        }
        tree.left = recursiveTree(nums, 0, center - 1);
        tree.right = recursiveTree(nums, center + 1, nums.length - 1);
        return tree;
    }

    public TreeNode recursiveTree(int[] nums, int from, int to) {
        if (to < from || nums.length <= to) {
            return null;
        }
        if (to == from) {
            return new TreeNode(nums[to]);
        }
        int center = from + (to - from) / 2;

        TreeNode tree = new TreeNode(nums[center]);
        tree.left = recursiveTree(nums, from, center - 1);
        tree.right = recursiveTree(nums, center + 1, to);

        return tree;
    }


    public int pivotIndex(int[] nums) {
        int sum = 0;
        for (int i = 1; i < nums.length; i++) {
            sum += nums[i];
        }
        int left = 0;
        if (left == sum) {
            return 0;
        }
        for (int i = 1; i < nums.length; i++) {
            left += nums[i - 1];
            sum -= nums[i];
            if (left == sum) {
                return i;
            }
        }
        return -1;
    }

    public String thousandSeparator(int n) {
        String s = String.valueOf(n);
        char[] c = s.toCharArray();

        StringBuilder sb = new StringBuilder();
        int pos = 0;
        for (int i = c.length - 1; 0 <= i; i--) {
            if (0 < pos && pos % 3 == 0) {
                sb.append(".");
            }
            sb.append(c[i]);
            pos++;
        }
        String result = sb.reverse().toString();
        return result;
    }

    public String longestNiceSubstring(String s) {
        char[] c = s.toCharArray();
        String result = "";
        for (int left = 0; left < c.length - 1; left++) {
            Set<Integer> chars = new HashSet<>();
            Set<Integer> uchars = new HashSet<>();
            int cc = c[left];
            if (Character.isUpperCase(cc)) {
                uchars.add(cc);
            } else {
                chars.add(cc);
            }
            for (int right = left + 1; right < c.length; right++) {
                int ccc = c[right];
                if (Character.isUpperCase(ccc)) {
                    uchars.add(ccc);
                } else {
                    chars.add(ccc);
                }
                if (uchars.size() == chars.size()) {
                    boolean isCorrect = true;
                    for (int a : uchars) {
                        if (!chars.contains(a + 32)) {
                            isCorrect = false;
                            break;
                        }
                    }
                    if (isCorrect) {
                        if ((right + 1 - left > result.length())) {
                            result = s.substring(left, right + 1);
                        }
                    }
                }
            }
        }
        return result;
    }

    public int numWaterBottles(int numBottles, int numExchange) {
        int result = numBottles;
        while (numExchange <= numBottles) {
            int mod = numBottles % numExchange;
            int div = numBottles / numExchange;
            numBottles = mod + div;
            result += div;
        }
        return result;
    }

    public int firstUniqChar(String s) {
        char[] c = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (Character cc : c) {
            map.put(cc, map.getOrDefault(cc, 0) + 1);
        }

        for (int i = 0; i < c.length; i++) {
            if (map.get(c[i]) == 1) {
                return i;
            }
        }
        return -1;
    }

    public int numUniqueEmails(String[] emails) {

        Set<String> set = new HashSet<>();
        for (String email : emails) {
            String name = email.split("@")[0];
            String domain = email.split("@")[1];
            name = name.replaceAll("\\.", "");
            if (name.contains("+")) {
                name = name.split("\\+")[0];
            }
            set.add(name + "@" + domain);
        }
        return set.size();
    }

    public int maxSubArray(int[] nums) {
        int result = nums[0];
        int currentMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            currentMax = Math.max(nums[i], nums[i] + currentMax);
            result = Math.max(result, currentMax);
        }
        return result;
    }

    public String makeGood(String s) {
        LinkedList<Character> chars = new LinkedList<>();
        for (char c : s.toCharArray()) {
            chars.add(c);
        }
        for (int i = 0; i < chars.size() - 1; i++) {
            char c = chars.get(i);
            char cc = chars.get(i + 1);
            if ((c == cc + 32) || (c == cc - 32)) {
                chars.remove(i + 1);
                chars.remove(i);
                i = -1;
            }
        }
        return chars.stream().map(String::valueOf).collect(Collectors.joining());
    }

    public int maxProfit2(int[] prices) {
        int pre = prices[0];
        int base = pre;
        int result = 0;
        int i = 1;
        while (i < prices.length) {
            if (pre < prices[i]) {
                while (i < prices.length && pre <= prices[i]) {
                    // 上がった
                    pre = prices[i];
                    i++;
                }
                result += pre - base;
            } else if (pre == prices[i]) {
                i++;
            } else if (prices[i] < pre) {
                while (i < prices.length && prices[i] <= pre) {
                    // 下がった
                    pre = prices[i];
                    base = pre;
                    i++;
                }
            }
        }
        return result;
    }

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {

        if (root1 == null && root2 == null) {
            return null;
        }
        TreeNode node = new TreeNode(0);
        setNodeVal(node, root1, root2);
        addTree(node, root1, root2);
        return node;
    }

    public void setNodeVal(TreeNode node, TreeNode root1, TreeNode root2) {
        int val;
        if (root1 == null) {
            val = root2.val;
        } else if (root2 == null) {
            val = root1.val;
        } else {
            val = root1.val + root2.val;
        }
        node.val = val;
    }

    public void addTree(TreeNode node, TreeNode root1, TreeNode root2) {
        setNodeVal(node, root1, root2);

        TreeNode right1 = null;
        TreeNode right2 = null;
        if (root1 != null && root1.right != null) {
            right1 = root1.right;
        }
        if (root2 != null && root2.right != null) {
            right2 = root2.right;
        }
        if (!(right1 == null && right2 == null)) {
            node.right = new TreeNode(0);
            addTree(node.right, right1, right2);
        }

        TreeNode left1 = null;
        TreeNode left2 = null;
        if (root1 != null && root1.left != null) {
            left1 = root1.left;
        }
        if (root2 != null && root2.left != null) {
            left2 = root2.left;
        }
        if (!(left1 == null && left2 == null)) {
            node.left = new TreeNode(0);
            addTree(node.left, left1, left2);
        }

    }


    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> result = new HashSet<>();
        for (int j : nums1) {
            set.add(j);
        }
        for (int j : nums2) {
            if (set.contains(j)) {
                result.add(j);
            }
        }
        int[] nums = new int[result.size()];
        int i = 0;
        for (Integer v : result) {
            nums[i] = v;
            i++;
        }
        return nums;

    }

    static class KthLargest {
        PriorityQueue<Integer> pq;
        int kk;

        public KthLargest(int k, int[] nums) {
            pq = new PriorityQueue<>(k, Integer::compare);
            kk = k;
            for (int num : nums) {
                add(num);
            }
        }

        public int add(int val) {
            if (pq.size() < kk) {
                pq.add(val);
            } else {
                Integer min = pq.poll();
                if (min < val) {
                    min = val;
                }
                pq.add(min);
            }
            return pq.peek();
        }

    }

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ArrayDeque<Integer> q = new ArrayDeque<>();
        while (head != null) {
            q.push(head.val);
            head = head.next;
        }
        ListNode result = new ListNode(q.pop());
        ListNode tmp = result;
        while (!q.isEmpty()) {
            tmp.next = new ListNode(q.pop());
            tmp = tmp.next;
        }
        return result;
    }

    public int[] decrypt(int[] code, int k) {
        int[] result = new int[code.length];
        if (k == 0) {
            return result;
        } else if (k > 0) {
            for (int i = 0; i < code.length; i++) {
                int sum = 0;
                for (int j = 1; j <= k; j++) {
                    int pos = i + j;
                    if (code.length <= pos) {
                        pos -= code.length;
                    }
                    sum += code[pos];
                }
                result[i] = sum;
            }
        } else {
            for (int i = 0; i < code.length; i++) {
                int sum = 0;
                for (int j = 1; j <= -k; j++) {
                    int pos = i - j;
                    if (pos < 0) {
                        pos += code.length;
                    }
                    sum += code[pos];
                }
                result[i] = sum;
            }

        }
        return result;
    }

    public int distributeCandies(int[] candyType) {
        Set<Integer> set = new HashSet<>();
        for (int i : candyType) {
            set.add(i);
        }
        int limit = candyType.length / 2;
        if (set.size() < limit) {
            limit = set.size();
        }
        return limit;
    }

    boolean hasPath = false;

    public boolean hasPathSum(TreeNode root, int targetSum) {

        if (root == null) {
            return false;
        }
        recursivePath(root, targetSum, 0);
        return hasPath;
    }

    public void recursivePath(TreeNode node, int targetSum, int current) {
        current += node.val;
        if (node.left == null && node.right == null) {
            if (!hasPath && current == targetSum) {
                hasPath = true;
            }
            return;
        }
        if (node.right != null) {
            recursivePath(node.right, targetSum, current);
        }
        if (node.left != null) {
            recursivePath(node.left, targetSum, current);
        }

    }

    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int result = 0;
        for (int i = 0; i < startTime.length; i++) {
            if (startTime[i] <= queryTime && queryTime <= endTime[i]) {
                result++;
            }
        }
        return result;
    }

    public int maxProduct(int[] nums) {
        Arrays.sort(nums);
        return (nums[nums.length - 1] - 1) * (nums[nums.length - 2] - 1);
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            ListNode next = current.next;
            while (next != null && current.val == next.val) {
                next = next.next;
            }
            current.next = next;
            current = next;
        }
        return head;
    }

    public boolean isValid(String s) {
        char[] c = s.toCharArray();
        Deque<Character> queue = new ArrayDeque<>();
        for (int i = c.length - 1; 0 <= i; i--) {
            if (c[i] == '(' && !queue.isEmpty()) {
                char cc = queue.pop();
                if (cc != ')') {
                    queue.add(cc);
                    queue.add(c[i]);
                }
            } else if (c[i] == '{' && !queue.isEmpty()) {
                char cc = queue.pop();
                if (cc != '}') {
                    queue.add(cc);
                    queue.add(c[i]);
                }

            } else if (c[i] == '[' && !queue.isEmpty()) {
                char cc = queue.pop();
                if (cc != ']') {
                    queue.add(cc);
                    queue.add(c[i]);
                }

            } else {
                queue.push(c[i]);
            }
        }

        if (queue.isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        Set<ListNode> set = new HashSet<>();
        while (head.next != null) {
            if (set.contains(head)) {
                return true;
            }
            set.add(head);
            head = head.next;
        }

        return false;
    }

    public int searchInsert(int[] nums, int target) {
        int left = -1;
        int right = nums.length;
        while (right - left > 1) {
            int mid = (right - left) / 2 + left;
            if (nums.length <= mid || nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }

        }
        return right;
    }

    int min = Integer.MAX_VALUE;
    int max = 0;

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        recursiveTreeMax(root, 1);
        return max;

    }

    public void recursiveTreeMax(TreeNode tree, int depth) {
        if (tree.left == null && tree.right == null) {
            max = Math.max(depth, max);
            return;
        }
        if (tree.left != null) {
            recursiveTreeMax(tree.left, depth + 1);
        }

        if (tree.right != null) {
            recursiveTreeMax(tree.right, depth + 1);
        }
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        recursiveTree(root, 1);
        return min;
    }

    public void recursiveTree(TreeNode tree, int depth) {
        if (tree.left == null && tree.right == null) {
            min = Math.min(depth, min);
            return;
        }
        if (tree.left != null) {
            recursiveTree(tree.left, depth + 1);
        }

        if (tree.right != null) {
            recursiveTree(tree.right, depth + 1);
        }
    }

    public void moveZeroes(int[] nums) {

        int pos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                continue;
            }
            nums[pos] = nums[i];
            pos++;
        }
        for (int i = pos; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public boolean isPowerOfFour(int n) {
        if (n < 1) {
            return false;
        }
        while (n > 1) {
            if (n % 4 != 0) {
                return false;
            }
            n /= 4;
        }
        return true;
    }

    public boolean isPowerOfThree(int n) {
        if (n < 1) {
            return false;
        }
        while (n > 1) {
            if (n % 3 != 0) {
                return false;
            }
            n /= 3;
        }
        return true;
    }

    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        if (rec1[0] == rec1[2] || rec1[1] == rec1[3]) {
            return false;
        }
        if (rec2[0] == rec2[2] || rec2[1] == rec2[3]) {
            return false;
        }
        // 1上辺と2右縦
        if (rec1[0] < rec2[2] && rec1[3] > rec2[1] &&
                rec1[2] > rec2[2] && rec1[3] < rec2[3]) {
            return true;
        }
        // 1上辺と2左縦
        if (rec1[0] < rec2[0] && rec1[3] > rec2[1] &&
                rec1[2] > rec2[0] && rec1[3] < rec2[3]) {
            return true;
        }
        // 1下辺と2右縦
        if (rec1[0] < rec2[2] && rec1[1] > rec2[1] &&
                rec1[2] > rec2[2] && rec1[1] < rec2[3]) {
            return true;
        }
        // 1下辺と2左縦
        if (rec1[0] < rec2[0] && rec1[1] > rec2[1] &&
                rec1[2] > rec2[0] && rec1[1] < rec2[3]) {
            return true;
        }
        // 1左縦と2上辺
        if (rec1[0] > rec2[0] && rec1[1] < rec2[3] &&
                rec1[0] < rec2[2] && rec1[3] > rec2[3]) {
            return true;
        }
        // 1左縦と2下辺
        if (rec1[0] > rec2[0] && rec1[1] < rec2[1] &&
                rec1[0] < rec2[2] && rec1[3] > rec2[1]) {
            return true;
        }
        // 1右縦と2上辺
        if (rec1[2] > rec2[0] && rec1[1] < rec2[3] &&
                rec1[2] < rec2[2] && rec1[3] > rec2[3]) {
            return true;
        }
        // 1右縦と2下辺
        if (rec1[2] > rec2[0] && rec1[1] < rec2[1] &&
                rec1[2] < rec2[2] && rec1[3] > rec2[1]) {
            return true;
        }
        // rec2が内包
        if (rec1[0] > rec2[0] && rec1[1] > rec2[1] && // 左下
                rec1[0] > rec2[0] && rec1[3] < rec2[3] && // 左上
                rec1[2] < rec2[2] && rec1[1] > rec2[1] && //右下
                rec1[2] < rec2[2] && rec1[3] < rec2[3]) {
            return true;
        }
        // rec1が内包
        if (rec2[0] > rec1[0] && rec2[1] > rec1[1] && // 左下
                rec2[0] > rec1[0] && rec2[3] < rec1[3] && // 左上
                rec2[2] < rec1[2] && rec2[1] > rec1[1] && //右下
                rec2[2] < rec1[2] && rec2[3] < rec1[3]) {
            return true;
        }
        return false;
    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        char[] c = String.valueOf(x).toCharArray();
        for (int i = 0; i < c.length; i++) {
            int j = c.length - 1 - i;
            if (c[i] != c[j]) {
                return false;
            }
        }
        return true;
    }

    public int numPrimeArrangements(int n) {
        int prime = 0;
        for (int i = 1; i <= n; i++) {
            if (isPrime(i)) {
                prime++;
            }
        }
        long result = 1;
        for (int i = 1; i <= prime; i++) {
            result *= i;
            result %= 1_000_000_007;
        }
        for (int i = 1; i <= n - prime; i++) {
            result *= i;
            result %= 1_000_000_007;
        }
        return (int) result;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode ap = headA;
        ListNode bp = headB;
        while (ap != bp) {
            // a,bに共通部分があればそこで止まる
            if (ap == null) {
                ap = headB;
            } else {
                ap = ap.next;
            }

            if (bp == null) {
                bp = headA;
            } else {
                bp = bp.next;
            }
        }
        return ap;
    }

    public int getMaximumGenerated(int n) {
        int[] nn = new int[102];
        nn[0] = 0;
        nn[1] = 1;
        for (int i = 1; i <= 50; i++) {
            nn[2 * i] = nn[i];
            nn[2 * i + 1] = nn[i] + nn[i + 1];
        }
        int result = 0;
        for (int i = 0; i <= n; i++) {
            result = Math.max(nn[i], result);
        }
        return result;
    }

    public boolean isPrime(int x) {
        if (x == 1) {
            return false;
        }
        for (long i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int sumOfLeftLeaves(TreeNode root) {
        int result = 0;
        if (root == null) {
            return result;
        }
        if (root.left != null) {
            result += recursiveSum(root.left, true);
        }
        if (root.right != null) {
            result += recursiveSum(root.right, false);
        }
        return result;
    }

    public int recursiveSum(TreeNode tree, boolean fromLeft) {
        if (tree.right == null && tree.left == null) {
            if (fromLeft) {
                return tree.val;
            }
        }
        int tmp = 0;
        if (tree.left != null) {
            tmp += recursiveSum(tree.left, true);
        }
        if (tree.right != null) {
            tmp += recursiveSum(tree.right, false);
        }
        return tmp;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> one = recursiveTree(root, p, new ArrayList<>());
        List<TreeNode> two = recursiveTree(root, q, new ArrayList<>());
        if (one.size() < two.size()) {
            for (int i = two.size() - 1; 0 <= i; i--) {
                if (i < one.size()) {
                    if (two.get(i).equals(one.get(i))) {
                        return two.get(i);
                    }
                }
            }
        } else {
            for (int i = one.size() - 1; 0 <= i; i--) {
                if (i < two.size()) {
                    if (two.get(i).equals(one.get(i))) {
                        return two.get(i);
                    }
                }
            }
        }
        return null;
    }

    public List<TreeNode> recursiveTree(TreeNode root, TreeNode p, List<TreeNode> parents) {
        List<TreeNode> pp = new ArrayList<>(parents);
        pp.add(root);
        if (root.val == p.val) {
            return pp;
        } else {
            List<TreeNode> ret = pp;
            if (root.left != null) {
                ret = recursiveTree(root.left, p, pp);
            }
            if (root.right != null && ret.get(ret.size() - 1).val != p.val) {
                ret = recursiveTree(root.right, p, pp);
            }
            return ret;
        }
    }

    public int longestPalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] c = s.toCharArray();
        for (char cc : c) {
            map.put(cc, map.getOrDefault(cc, 0) + 1);
        }
        int result = 0;
        boolean isOdd = false;

        for (Character cc : new ArrayList<>(map.keySet())) {
            if (map.get(cc) % 2 == 0) {
                result += map.get(cc);
            } else {
                isOdd = true;
                result += map.get(cc) - 1;
            }
        }
        if (isOdd) {
            result++;
        }
        return result;
    }

    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 0; i < cost.length; i++) {
            dp[i + 1] = Math.min(dp[i + 1], dp[i] + cost[i]);
            if (i + 2 < dp.length) {
                dp[i + 2] = Math.min(dp[i + 2], dp[i] + cost[i]);
            }
        }
        return dp[cost.length];
    }

    public int[][] imageSmoother(int[][] M) {
        int[] x = new int[]{-1, 0, 1, -1, 0, 1, -1, 0, 1};
        int[] y = new int[]{-1, -1, -1, 0, 0, 0, 1, 1, 1};
        int[][] result = new int[M.length][M[0].length];

        for (int i = 0; i < M.length; i++) {
            int[] row = M[i];
            for (int j = 0; j < row.length; j++) {
                int total = 0;
                int count = 0;
                for (int k = 0; k < 9; k++) {
                    if ((0 <= j + x[k] && j + x[k] < row.length) &&
                            (0 <= i + y[k] && i + y[k] < M.length)) {
                        total += M[i + y[k]][j + x[k]];
                        count++;
                    }
                }
                result[i][j] = total / count;
            }
        }
        return result;
    }

    public String reverseStr(String s, int k) {
        char[] c = s.toCharArray();
        char[] reverse = new char[c.length];
        int pos = 0;
        while (true) {
            if (pos + 2 * k <= c.length) {
                for (int i = 0; i < k; i++) {
                    reverse[pos + i] = c[pos + k - 1 - i];
                }
                for (int i = k; i < 2 * k; i++) {
                    reverse[pos + i] = c[pos + i];
                }
            } else if (pos <= c.length && c.length <= pos + k) {
                for (int i = 0; pos + i < c.length; i++) {
                    reverse[pos + i] = c[c.length - 1 - i];
                }
                break;
            } else if (pos <= c.length && c.length < pos + 2 * k) {
                for (int i = 0; i < k; i++) {
                    reverse[pos + i] = c[pos + k - 1 - i];
                }
                for (int i = k; pos + i < c.length; i++) {
                    reverse[pos + i] = c[pos + i];
                }
                break;
            }
            pos += 2 * k;
        }
        return new String(reverse);
    }

    public String longestWord(String[] words) {
        String result = "";
        Set<String> set = new HashSet<>(Arrays.asList(words));

        for (int i = 0; i < words.length; i++) {
            String source = words[i];
            if (result.length() < source.length() || (result.length() == source.length() && source.compareTo(result) < 0)) {
                boolean correct = true;
                for (int j = 1; j <= source.length(); j++) {
                    if (!set.contains(source.substring(0, j))) {
                        correct = false;
                        break;
                    }
                }
                if (correct) {
                    result = source;
                }
            }
        }
        return result;
    }

    public void duplicateZeros(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int i = arr.length - 1; 0 <= i; i--) {
            list.add(arr[i]);
            if (arr[i] == 0) {
                list.add(arr[i]);
            }
        }
        Collections.reverse(list);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
    }

    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (true) {
            int nn = calc(n);
            if (nn == 1) {
                return true;
            }
            if (set.contains(nn)) {
                return false;
            }
            set.add(nn);
            n = nn;
        }
    }

    public int calc(int n) {
        int result = 0;
        while (n > 0) {
            int div = n / 10;
            int mod = n % 10;
            result += mod * mod;
            n = div;
        }
        return result;
    }

    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            if (i + 1 <= n) {
                dp[i + 1] += dp[i];
            }
            if (i + 2 <= n) {
                dp[i + 2] += dp[i];
            }
        }
        return dp[n];
    }

    public int findJudge(int N, int[][] trust) {
        int[] n = new int[N + 1];
        boolean[] trusted = new boolean[N + 1];
        Arrays.fill(trusted, true);

        for (int i = 0; i < trust.length; i++) {
            int from = trust[i][0];
            trusted[from] = false;
            int to = trust[i][1];
            n[to]++;
        }
        int result = -1;
        for (int i = 1; i <= N; i++) {
            if ((n[i] == N - 1) && (trusted[i])) {
                result = i;
            }
        }
        return result;
    }


    public boolean isSubsequence(String s, String t) {
        char[] ss = s.toCharArray();
        char[] tt = t.toCharArray();
        int pos = 0;
        if (ss.length == 0) {
            return true;
        }
        if (tt.length == 0) {
            return false;
        }

        for (char c : tt) {
            if (c == ss[pos]) {
                pos++;
            }
            if (pos == ss.length) {
                break;
            }
        }
        return pos == ss.length;
    }

    public int dayOfYear(String date) {
        int[] days = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int year = Integer.parseInt(date.split("-")[0]);
        int month = Integer.parseInt(date.split("-")[1]);
        int day = Integer.parseInt(date.split("-")[2]);

        int result = 0;
        for (int i = 0; i < month - 1; i++) {
            result += days[i];
            if (i == 1 && uruYear(year)) {
                result++;
            }
        }
        result += day;
        return result;

    }

    public boolean uruYear(int y) {
        if (y % 400 == 0) {
            return true;
        } else if (y % 100 == 0) {
            return false;
        } else if (y % 4 == 0) {
            return true;
        }
        return false;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= k; i++) {
            if (nums.length <= i) {
                return false;
            }
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            if (1 < map.get(nums[i])) {
                return true;
            }
        }
        if (nums.length <= k + 1) {
            return false;
        }

        for (int i = k + 1; i < nums.length; i++) {
            map.put(nums[i - (k + 1)], map.get(nums[i - (k + 1)]) - 1);
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            if (1 < map.get(nums[i])) {
                return true;
            }
        }
        return false;
    }

    public int mySqrt(int x) {
        // y^2 <= x
        long i = 0;
        while (i * i <= x) {
            i++;
        }
        return (int) (i - 1);
    }

    public int robotSim(int[] commands, int[][] obstacles) {
        Map<Integer, List<Integer>> xMap = new HashMap<>();
        Map<Integer, List<Integer>> yMap = new HashMap<>();
        for (int i = 0; i < obstacles.length; i++) {
            int x = obstacles[i][0];
            int y = obstacles[i][1];
            List<Integer> xtmp = xMap.getOrDefault(x, new ArrayList<>());
            xtmp.add(y);
            xMap.put(x, xtmp);

            List<Integer> ytmp = yMap.getOrDefault(y, new ArrayList<>());
            ytmp.add(x);
            yMap.put(y, ytmp);
        }
        int x = 0;
        int y = 0;
        int result = 0;
        // 0: up , 1:right , 2:down, 3: left
        int direction = 0;
        for (int i = 0; i < commands.length; i++) {
            int command = commands[i];
            if (command == -1) {
                // turn right
                direction++;
                if (3 < direction) {
                    direction = 0;
                }
            } else if (command == -2) {
                // turn left
                direction--;
                if (direction < 0) {
                    direction = 3;
                }
            } else {
                int xx = x;
                int yy = y;
                List<Integer> yList;
                List<Integer> xList;
                switch (direction) {
                    case 0:
                        // up
                        yy += command;
                        yList = xMap.get(xx);
                        if (yList != null) {
                            for (int tmp : yList) {
                                if (y < tmp && tmp <= yy) {
                                    yy = tmp - 1;
                                }
                            }
                        }
                        break;
                    case 1:
                        // right
                        xx += command;
                        xList = yMap.get(yy);
                        if (xList != null) {
                            for (int tmp : xList) {
                                if (x < tmp && tmp <= xx) {
                                    xx = tmp - 1;
                                }
                            }
                        }
                        break;
                    case 2:
                        // down
                        yy -= command;
                        yList = xMap.get(xx);
                        if (yList != null) {
                            for (int tmp : yList) {
                                if (yy <= tmp && tmp < y) {
                                    yy = tmp + 1;
                                }
                            }
                        }
                        break;
                    case 3:
                        // left
                        xx -= command;
                        xList = yMap.get(yy);
                        if (xList != null) {
                            for (int tmp : xList) {
                                if (xx <= tmp && tmp < x) {
                                    xx = tmp + 1;
                                }
                            }
                        }
                        break;
                }
                x = xx;
                y = yy;
                result = Math.max(result, x * x + y * y);
            }
        }
        return result;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preOrder(root, list);
        return list;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postOrder(root, list);
        return list;
    }

    public void postOrder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        postOrder(node.left, list);
        postOrder(node.right, list);
        list.add(node.val);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inOrder(root, list);
        return list;
    }

    public void preOrder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        preOrder(node.left, list);
        preOrder(node.right, list);
    }

    public void inOrder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        inOrder(node.left, list);
        list.add(node.val);
        inOrder(node.right, list);
    }

    public int lengthOfLastWord(String s) {
        String[] ss = s.split(" ");
        if (ss.length == 0) {
            return 0;
        }
        return ss[ss.length - 1].toCharArray().length;
    }

    public int thirdMax(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        List<Integer> list = new ArrayList<>(set);
        if (list.size() == 1) {
            return list.get(0);
        }
        if (list.size() == 2) {
            return Math.max(list.get(0), list.get(1));
        }
        list.sort(Collections.reverseOrder());
        return list.get(2);
    }


    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 1) {
                if (i + 1 < flowerbed.length && flowerbed[i + 1] == 1) {
                    return false;
                }
            }
            list.add(flowerbed[i]);
        }
        list.add(0);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 0) {
                if (i + 2 < list.size() && list.get(i + 1) == 0 && list.get(i + 2) == 0) {
                    n--;
                    i += 1;
                }
            }
        }
        return (n <= 0);
    }

    public int reverse(int x) {
        long rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            rev = 10 * rev + pop;
            if (rev > Integer.MAX_VALUE) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE) {
                return 0;
            }
        }
        return (int) rev;
    }

    public boolean buddyStrings(String A, String B) {
        char[] a = A.toCharArray();
        char[] b = B.toCharArray();
        if (a.length != b.length) {
            return false;
        }
        Character a1 = null, a2 = null;
        Character b1 = null, b2 = null;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                if (a1 == null) {
                    a1 = a[i];
                    b1 = b[i];
                } else if (a2 == null) {
                    a2 = a[i];
                    b2 = b[i];
                } else {
                    return false;
                }
            }
        }
        if (a1 == null) {
            // 同じ文字が2つあるか
            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < a.length; i++) {
                map.put(a[i], map.getOrDefault(a[i], 0) + 1);
                if (1 < map.get(a[i])) {
                    return true;
                }
            }
        }
        if (a2 == null) {
            return false;
        }
        return (a1.equals(b2) && b1.equals(a2));
    }

    public boolean validMountainArray(int[] arr) {
        if (arr.length < 3) {
            return false;
        }
        if (arr[0] >= arr[1]) {
            return false;
        }
        boolean isUp = true;
        int pre = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (pre == arr[i]) {
                return false;
            }
            if (isUp) {
                if (pre > arr[i]) {
                    isUp = false;
                }
            } else {
                if (pre < arr[i]) {
                    return false;
                }
            }
            pre = arr[i];
        }
        return !isUp;
    }

    public boolean isLongPressedName(String name, String typed) {
        char[] nn = name.toCharArray();
        char[] tt = typed.toCharArray();

        boolean result = true;

        int tpos = 0;
        for (int i = 0; i < nn.length; i++) {
            char n = nn[i];
            int len = 1;
            while (i + 1 < nn.length && n == nn[i + 1]) {
                i++;
                len++;
            }

            if (tt.length <= tpos) {
                result = false;
                break;
            }
            char t = tt[tpos];
            if (t != n) {
                result = false;
                break;
            }

            int tlen = 1;
            while (tpos + 1 < tt.length && t == tt[tpos + 1]) {
                tpos++;
                tlen++;
            }

            if (tlen < len) {
                result = false;
                break;
            }
            tpos++;
        }
        if (result && tpos != tt.length) {
            result = false;
        }

        return result;
    }

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int gpos = 0;
        for (int i = 0; i < s.length; i++) {
            if (gpos < g.length && g[gpos] <= s[i]) {
                gpos++;
            }
        }
        return gpos;
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] sell = new int[n];
        int[] buy = new int[n];
        if (n == 0) {
            return 0;
        }
        sell[n - 1] = prices[n - 1];
        for (int i = n - 2; 0 <= i; i--) {
            sell[i] = Math.max(sell[i + 1], prices[i]);
        }

        buy[0] = prices[0];
        for (int i = 1; i < n - 1; i++) {
            buy[i] = Math.min(buy[i - 1], prices[i]);
        }

        int result = 0;
        for (int i = 0; i < n - 1; i++) {
            result = Math.max(sell[i] - buy[i], result);
        }
        return result;
    }


    public int arrangeCoins(int n) {
        return binarySearch(n);
    }

    public int binarySearch(int n) {
        long left = -1;
        long right = (long) n + 1;
        while (1 < right - left) {
            long middle = left + (right - left) / 2;
            long value = middle * (middle + 1) / 2;
            if (n < value) {
                right = middle;
            } else {
                left = middle;
            }
        }
        return (int) left;
    }

    public String convertToBase7(int num) {
        StringBuilder sb = new StringBuilder();
        int base = Math.abs(num);
        if (num == 0) {
            return "0";
        }
        while (0 < base) {
            sb.append(base % 7);
            base = base / 7;
        }
        if (num < 0) {
            sb.append("-");
        }
        return sb.reverse().toString();
    }

    public String addStrings(String num1, String num2) {

        char[] a = (new StringBuilder(num1)).reverse().toString().toCharArray();
        char[] b = (new StringBuilder(num2)).reverse().toString().toCharArray();

        int limit = Math.max(a.length, b.length);
        StringBuilder sb = new StringBuilder();
        int next = 0;
        for (int i = 0; i < limit; i++) {
            int aa = 0;
            int bb = 0;
            if (i < a.length) {
                aa = a[i] - '0';
            }
            if (i < b.length) {
                bb = b[i] - '0';
            }
            int cc = aa + bb + next;
            sb.append(cc % 10);
            next = cc / 10;
        }
        if (0 < next) {
            sb.append(next);
        }
        return sb.reverse().toString();
    }

    public boolean detectCapitalUse(String word) {
        char[] c = word.toCharArray();

        boolean isUpper = (c[0] < 'a');
        for (int i = 1; i < c.length; i++) {
            if (i == 1) {
                if (isUpper && 'a' <= c[i]) {
                    isUpper = false;
                }
            }

            if (isUpper) {
                if ('a' <= c[i]) {
                    return false;
                }
            } else {
                if (c[i] < 'a') {
                    return false;
                }
            }
        }
        return true;
    }

    public int minDeletionSize(String[] A) {
        List<char[]> lists = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            lists.add(A[i].toCharArray());
        }

        int limit = lists.get(0).length;
        int num = 0;
        for (int j = 0; j < limit; j++) {
            char pre = lists.get(0)[j];
            for (int i = 1; i < lists.size(); i++) {
                char current = lists.get(i)[j];
                if (pre > current) {
                    num++;
                    break;
                }
                pre = current;
            }
        }
        return num;
    }

    public boolean containsDuplicate(int[] nums) {
        Map<Integer, Boolean> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], true);
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean backspaceCompare(String S, String T) {
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        return build(s).equals(build(t));
    }

    public String build(char[] s) {

        Deque<Character> q = new ArrayDeque<>();
        for (char value : s) {
            q.push(value);
        }

        int delete = 0;
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            char c = q.pop();
            if (c == '#') {
                delete++;
            } else {
                if (0 < delete) {
                    delete--;
                } else {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }


    Map<Integer, List<Integer>> map = new HashMap<>();

    public List<Double> averageOfLevels(TreeNode root) {
        recursive(0, root);
        List<Double> answer = new ArrayList<>();
        for (Integer key : map.keySet()) {
            List<Integer> layer = map.get(key);
            long sum = 0;
            for (Integer integer : layer) {
                sum += integer;
            }
            answer.add((double) sum / layer.size());
        }
        return answer;
    }


    public void recursive(int depth, TreeNode tree) {
        List<Integer> list = map.getOrDefault(depth, new ArrayList<>());
        list.add(tree.val);
        map.put(depth, list);

        if (tree.left != null) {
            recursive(depth + 1, tree.left);
        }

        if (tree.right != null) {
            recursive(depth + 1, tree.right);
        }
    }

    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> kk = new ArrayList<>();
        int nums = String.valueOf(K).length();
        for (int i = nums - 1; 0 <= i; i--) {
            int amari = (int) (K / Math.pow(10, i));
            K -= amari * Math.pow(10, i);
            kk.add(amari);
        }
        Collections.reverse(kk);

        List<Integer> list = new ArrayList<>();
        int nextDigit = 0;
        int limit = Math.max(A.length, kk.size());
        for (int i = 0; i < limit; i++) {
            int a = 0;
            int apos = A.length - i - 1;
            if (0 <= apos) {
                a = A[apos];
            }
            int k = 0;
            if (i < kk.size()) {
                k = kk.get(i);
            }

            int sum = a + k + nextDigit;
            nextDigit = sum / 10;
            list.add(sum % 10);
        }
        if (0 < nextDigit) {
            list.add(nextDigit);
        }
        Collections.reverse(list);

        return list;
    }

    public String addBinary(String a, String b) {

        char[] aa = new StringBuilder(a).reverse().toString().toCharArray();
        char[] bb = new StringBuilder(b).reverse().toString().toCharArray();
        List<String> list = new ArrayList<>();
        int limit = Math.max(aa.length, bb.length) + 1;
        int next = 0;
        for (int i = 0; i < limit; i++) {
            int one = 0;
            if (i < aa.length) {
                if (aa[i] == '1') {
                    one++;
                }
            }
            if (i < bb.length) {
                if (bb[i] == '1') {
                    one++;
                }
            }
            if (next == 1) {
                one++;
            }
            if (one == 3) {
                list.add("1");
                next = 1;
            } else if (one == 2) {
                list.add("0");
                next = 1;
            } else if (one == 1) {
                list.add("1");
                next = 0;
            } else {
                list.add("0");
                next = 0;
            }
        }

        if (list.isEmpty()) {
            list.add("0");
        } else {
            Collections.reverse(list);
            if (list.get(0).equals("0")) {
                list.remove(0);
            }
        }
        return String.join("", list);
    }
}
