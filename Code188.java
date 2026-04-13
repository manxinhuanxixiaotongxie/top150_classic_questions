/**
 给你一个整数数组 prices 和一个整数 k ，其中 prices[i] 是某支给定的股票在第 i 天的价格。

 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。也就是说，你最多可以买 k 次，卖 k 次。

 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 */
public class Code188 {
    /**
     * 跟买卖股票3一样的解法
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        int N = prices.length;
        // dp[][][0] = false dp[][][1] = true
        int[][][] dp = new int[N + 1][k + 1][2];
        // 最下面一层 全是0 在遍历过程中 要注意times > k 的情况
        for (int index = N - 1; index >= 0; index--) {
            for (int times = 0; times <= k; times++) {
                for (int canBuy = 0; canBuy <= 1; canBuy++) {
                    if (times >= k) {
                        dp[index][times][canBuy] = 0;
                    } else {
                        if (canBuy == 1) {
                            // 如果当前位置能买 那么当前位置可以做两个选择
                            // 买  也可以选择不买
                            // 当前位置选择买
                            int p1 = dp[index + 1][times][0] - prices[index];
                            int p2 = dp[index + 1][times][1];
                            dp[index][times][canBuy] = Math.max(p1, p2);
                        } else {
                            // 当前位置不可以买
                            // 那么 当前位置可以选择卖 也可以不卖
                            // 当前位置卖
                            int p1 = prices[index] + dp[index + 1][times + 1][1];
                            int p2 = dp[index + 1][times][0];
                            dp[index][times][canBuy] = Math.max(p1, p2);
                        }
                    }
                }
            }
        }
        return dp[0][0][1];
    }
}
