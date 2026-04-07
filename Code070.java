/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 */
public class Code070 {
    public int climbStairs(int n) {
        return process(n);
    }

    public int process(int rest) {
        if (rest < 0) {
            return 0;
        }
        if (rest == 0) {
            return 1;
        }
        return process(rest - 1) + process(rest - 2);
    }

    /**
     * 该动态规划
     *
     * @param n
     * @return
     */
    public int climbStairs2(int n) {
        // 该动态规划
        // 0 - n
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i] = (i >= 1 ? dp[i - 1] : 0) + (i - 2 >= 0 ? dp[i - 2] : 0);
        }
        return dp[n];
    }

    /**
     * 空间压缩
     *
     * @param n
     * @return
     */
    public int climbStairs3(int n) {
        // 该动态规划
        // dp[0] = 1
        // dp[1] = 1
        int before = 1;
        int beforeBefore = 1;
        int ans = 1;
        for (int i = 2; i <= n; i++) {
            ans = before + beforeBefore;
            beforeBefore = before;
            before = ans;
        }
        return ans;
    }
}
