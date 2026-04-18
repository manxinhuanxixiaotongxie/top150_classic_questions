import java.io.FileOutputStream;

/**
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * <p>
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * <p>
 * 你可以认为每种硬币的数量是无限的。
 *
 */
public class Code322 {
    public int coinChange(int[] coins, int amount) {
        int ans = process(coins, 0, amount);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public int process(int[] coins, int index, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (index == coins.length) {
            return Integer.MAX_VALUE;
        }
        int min = Integer.MAX_VALUE;
        for (int zhang = 0; zhang * coins[index] <= amount; zhang++) {
            int next = process(coins, index + 1, amount - zhang * coins[index]);
            if (next != Integer.MAX_VALUE) {
                // 接下来的拆分有效
                min = Math.min(min, next + zhang);
            }
        }
        return min;
    }

    /**
     * 改动态规划
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange2(int[] coins, int amount) {
        int N = coins.length;
        int[][] dp = new int[N + 1][amount + 1];
        // 最后一行
        for (int j = 1; j <= amount; j++) {
            dp[N][j] = Integer.MAX_VALUE;
        }
        // 第一列是不需要填写的 默认就是0
        // 从倒数第二行开始进行填写
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 1; rest <= amount; rest++) {
                dp[index][rest] = Integer.MAX_VALUE;
                for (int zhang = 0; zhang * coins[index] <= rest; zhang++) {
                    int next = rest - zhang * coins[index] >= 0 ? dp[index + 1][rest - zhang * coins[index]] : Integer.MAX_VALUE;
                    if (next != Integer.MAX_VALUE) {
                        // 接下来的拆分有效
                        dp[index][rest] = Math.min(dp[index][rest], next + zhang);
                    }
                }
            }

        }
        return dp[0][amount] == Integer.MAX_VALUE ? -1 : dp[0][amount];
    }

    /**
     * 四边形不等式技巧 能够剩下一阶
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange3(int[] coins, int amount) {
        int N = coins.length;
        int[][] dp = new int[N + 1][amount + 1];
        // 最后一行
        for (int j = 1; j <= amount; j++) {
            dp[N][j] = Integer.MAX_VALUE;
        }
        // 第一列是不需要填写的 默认就是0
        // 从倒数第二行开始进行填写
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 1; rest <= amount; rest++) {
                dp[index][rest] = Integer.MAX_VALUE;
//                for (int zhang = 0; zhang * coins[index] <= rest; zhang++) {
//                    int next = rest - zhang * coins[index] >= 0 ? dp[index + 1][rest - zhang * coins[index]] : Integer.MAX_VALUE;
//                    if (next != Integer.MAX_VALUE) {
//                        // 接下来的拆分有效
//                        dp[index][rest] = Math.min(dp[index][rest], next + zhang);
//                    }
//                }
                // 为什么可以省下一阶 原因是因为当前行的元素都依赖下一行的元素的值 实际上 有很多重复为止已经被计算过了
                if (rest - coins[index] >= 0 && dp[index][rest - coins[index]] != Integer.MAX_VALUE) {
                    dp[index][rest] = Math.min(dp[index][rest], dp[index][rest - coins[index]] + 1);
                }
                dp[index][rest]  = Math.min(dp[index][rest], dp[index + 1][rest]);
            }

        }
        return dp[0][amount] == Integer.MAX_VALUE ? -1 : dp[0][amount];
    }
}
