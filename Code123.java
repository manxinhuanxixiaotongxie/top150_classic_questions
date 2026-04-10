/**
 * 买卖股票问题3
 * <p>
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * <p>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 */
public class Code123 {
    /**
     * 最多完成两笔交易
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        return process(prices, 0, 2 << 1, 0, true);
    }

    public int process(int[] prices, int index, int k, int times, boolean canBuy) {
        if (times >= k) {
            return 0;
        }

        if (index == prices.length) {
            return 0;
        }
        int maxProfit = 0;
        if (canBuy) {
            // 如果当前位置能买 那么当前位置可以做两个选择
            // 买  也可以选择不买
            // 当前位置选择买
            int p1 = process(prices, index + 1, k, times + 1, false) - prices[index];
            int p2 = process(prices, index + 1, k, times, true);
            maxProfit = Math.max(p1, p2);
        } else {
            // 当前位置不可以买
            // 那么 当前位置可以选择卖 也可以不卖
            // 当前位置卖
            int p1 = prices[index] + process(prices, index + 1, k, times + 1, true);
            int p2 = process(prices, index + 1, k, times, false);
            maxProfit = Math.max(p1, p2);
        }
        return maxProfit;
    }

    /**
     * 节省空间的做法
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        return process2(prices, 0, 2, 0, true);
    }

    public int process2(int[] prices, int index, int k, int times, boolean canBuy) {
        if (times >= k) {
            return 0;
        }

        if (index == prices.length) {
            return 0;
        }
        int maxProfit = 0;
        if (canBuy) {
            // 如果当前位置能买 那么当前位置可以做两个选择
            // 买  也可以选择不买
            // 当前位置选择买
            int p1 = process2(prices, index + 1, k, times, false) - prices[index];
            int p2 = process2(prices, index + 1, k, times, true);
            maxProfit = Math.max(p1, p2);
        } else {
            // 当前位置不可以买
            // 那么 当前位置可以选择卖 也可以不卖
            // 当前位置卖
            int p1 = prices[index] + process2(prices, index + 1, k, times + 1, true);
            int p2 = process2(prices, index + 1, k, times, false);
            maxProfit = Math.max(p1, p2);
        }
        return maxProfit;
    }

    /**
     * 改动态规划
     *
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        int k = 2;
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
