package leetcode75;

public class Code062 {


    public int[] xWays;
    public int[] yWays;

    public void init() {
        // 初始化
        // 下 右
        xWays = new int[]{1, 0};
        yWays = new int[]{0, 1};
    }

    /**
     * 暴力递归 超时
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        init();
        return process(m - 1, n - 1, 0, 0);
    }

    public int process(int m, int n, int r, int c) {
        if (r > m || c > n) {
            return 0;
        }
        if (r == m && c == n) {
            return 1;
        } else {
            // 分情况讨论
            int ans = 0;

            for (int i = 0; i < xWays.length; i++) {
                int nextR = xWays[i] + r;
                int nextC = yWays[i] + c;
                ans += process(m, n, nextR, nextC);
            }
            return ans;
        }
    }

    /**
     * 改成动态规划
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths2(int m, int n) {
        init();
        // r的范围是0-m-1  c的范围是0- n-1
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = 1;
        for (int r = m - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                for (int i = 0; i < xWays.length; i++) {
                    int nextR = xWays[i] + r;
                    int nextC = yWays[i] + c;
                    if (nextR < m && nextC < n) {
                        dp[r][c] += dp[nextR][nextC];
                    }
                }
            }
        }
        return dp[0][0];
    }

    /**
     * 有空间压缩的做法
     * <p>
     * 分析位置依赖 当前r c位置依赖右侧的位置以及下侧的位置
     * <p>
     * 将数组压缩成一维数组
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths3(int m, int n) {
        init();
        // r的范围是0-m-1  c的范围是0- n-1
        int[] dp = new int[n];
        dp[n-1] = 1;
        for (int c = n - 2; c >= 0; c--) {
            dp[c] += dp[c + 1];
        }
        // 普遍位置
        for (int r = m - 2; r >= 0; r--) {
            // dp[c] 保留的是下方（r+1行）的值，dp[c+1] 已更新为右方（r行）的值
            // 从右往左遍历，c=n-1 时只有下方贡献，无需更新；c<n-1 时加上右方
            for (int c = n - 2; c >= 0; c--) {
                dp[c] += dp[c + 1];
            }
        }

        return dp[0];
    }
}
