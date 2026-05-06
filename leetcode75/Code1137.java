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

    /**
     * log(n)解法
     * 利用矩阵快速幂
     */
    public int tribonacci4(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        int[][] base = new int[][]{
                {1, 1, 1},
                {1, 0, 0},
                {0, 1, 0}
        };

        // 求的是base的n-2次幂
        int[][] found = new int[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        n = n - 2;
        while (n != 0) {
            if ((n & 1) != 0) {
                // found * base
                found = matrixMul(found, base);
            }
            // base * base
            base = matrixMul(base, base);
            n >>= 1;
        }

        return found[0][0] + found[0][1];
    }

    // 两个数组矩阵相乘
    public int[][] matrixMul(int[][] mat1, int[][] mat2) {
        // 第一个矩阵的列与第二个矩阵的行是相等的
        int[][] ans = new int[mat1.length][mat2[0].length];
        for (int r = 0; r < mat1.length; r++) {
            for (int c = 0; c < mat2[0].length; c++) {
                // 行列的计算方式
                for (int k = 0; k < mat2[0].length; k++) {
                    ans[r][c] += mat1[r][k] * mat2[k][c];
                }
            }
        }
        return ans;
    }
}
