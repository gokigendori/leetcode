package com.gokigendori.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Easy {
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
        for (int i = 0; i < s.length; i++) {
            q.push(s[i]);
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
            for (int i = 0; i < layer.size(); i++) {
                sum += layer.get(i);
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

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
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