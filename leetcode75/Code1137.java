package leetcode75;

/**
 * 泰波那契序列 Tn 定义如下：
 * <p>
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 * <p>
 * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
 */
public class Code1137 {

    public int tribonacci(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        return process(n);
    }

    public int process(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        return process(n - 3) + process(n - 2) + process(n - 1);
    }

    public int tribonacci2(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        if (n >= 1) dp[1] = 1;
        if (n >= 2) dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        return dp[n];
    }

    /**
     * 空间复杂度O（1）
     *
     * @param n
     * @return
     */
    public int tribonacci3(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        int ans = 0;
        int n3 = 0, n2 = 1, n1 = 1;

        for (int i = 3; i <= n; i++) {
            ans = n1 + n2 + n3;
            n3 = n2;
            n2 = n1;
            n1 = ans;
        }
        return ans;
    }


}
