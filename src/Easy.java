import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Easy {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> one = recursive(root, p, new ArrayList<>());
        List<TreeNode> two = recursive(root, q, new ArrayList<>());
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

    public List<TreeNode> recursive(TreeNode root, TreeNode p, List<TreeNode> parents) {
        List<TreeNode> pp = new ArrayList<>(parents);
        pp.add(root);
        if (root.val == p.val) {
            return pp;
        } else {
            List<TreeNode> ret = pp;
            if (root.left != null) {
                ret = recursive(root.left, p, pp);
            }
            if (root.right != null && ret.get(ret.size() - 1).val != p.val) {
                ret = recursive(root.right, p, pp);
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
}
