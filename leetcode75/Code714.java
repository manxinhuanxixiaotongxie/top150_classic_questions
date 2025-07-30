package leetcode75;

/**
 * 给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
 * <p>
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * <p>
 * 返回获得利润的最大值。
 * <p>
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 */
public class Code714 {
    public int maxProfit(int[] prices, int fee) {
        return process(prices, fee, 0, false);
    }

    public int process(int[] prices, int fee, int index, boolean canSell) {
        if (index == prices.length) {
            return 0;
        } else {
            if (canSell) {
                // 能够交易卖出
                return Math.max(process(prices, fee, index + 1, true), process(prices, fee, index + 1, false) + prices[index] - fee);
            } else {
                // 能够操作买入
                return Math.max(process(prices, fee, index + 1, false), process(prices, fee, index + 1, true) - prices[index]);
            }
        }
    }

    /**
     * 改成动态规划
     *
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit2(int[] prices, int fee) {
        int[][] dp = new int[prices.length + 1][2];
        for (int i = prices.length - 1; i >= 0; i--) {
            //[0]    [1]
            // false true
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    // false
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i+1][1] - prices[i]);
                }else {
                    // true
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i+1][0] + prices[i] - fee);
                }
            }
        }
        return dp[0][0];
    }

    /**
     * 空间压缩
     *
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit3(int[] prices, int fee) {
        int[] dp = new int[2];
        for (int i = prices.length - 1; i >= 0; i--) {
            int downFalse = dp[0];
            int downTrue = dp[1];
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    // false
                    dp[j] = Math.max(downFalse, dp[j + 1] - prices[i]);
                }else {
                    // true
                    dp[j] = Math.max(downTrue, downFalse + prices[i] - fee);
                }
            }
        }
        return dp[0];
    }
}
