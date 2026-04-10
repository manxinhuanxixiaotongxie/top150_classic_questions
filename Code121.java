public class Code121 {
    public int maxProfit(int[] prices) {
        // 获取的最大利润
        // 假设必须在当前天卖出的话 那么只需要在之前的最低点买入就可以
        int ans = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            // 必须在当前位置卖出
            ans = Math.max(ans, Math.max(0, prices[i] - min));
            min = Math.min(min, prices[i]);
        }

        return ans;
    }
}
