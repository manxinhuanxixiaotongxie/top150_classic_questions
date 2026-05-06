package leetcode75;

import java.util.Arrays;

/**
 * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 * <p>
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
 * <p>
 * 请你计算并返回达到楼梯顶部的最低花费。
 * <p>
 * 提示：
 * <p>
 * 2 <= cost.length <= 1000
 * 0 <= cost[i] <= 999
 *
 */
public class Code746 {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        return Math.min(process(cost, n, 0), process(cost, n, 1));
    }

    public int process(int[] cost, int n, int index) {
        if (index >= n) {
            // 已经到了顶部
            return 0;
        } else {
            // 从index出发消费的最小费用
            // 有两种情况可以走
            // 第一种情况 向上走一个台阶
            int p1 = process(cost, n, index + 1) + cost[index];
            // 第二种情况 向上走两个台阶
            int p2 = process(cost, n, index + 2) + cost[index];
            return Math.min(p1, p2);
        }
    }

    /**
     * 改成动态规划
     * <p>
     * 不是最优解
     *
     */
    public int minCostClimbingStairs2(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[n] = 0;
        for (int index = n - 1; index >= 0; index--) {
            dp[index] = Math.min(dp[index], dp[index + 1] + cost[index]);
            if (index + 2 <= n) {
                dp[index] = Math.min(dp[index], dp[index + 2] + cost[index]);
            }
        }
        return Math.min(dp[0], dp[1]);
    }

    /**
     * 空间压缩
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs3(int[] cost) {
        int n = cost.length;
        int after = cost[n - 1];
        int afterAfter = 0;
        for (int index = n - 2; index >= 0; index--) {
            int cur = after + cost[index];
            if (index + 2 <= n) {
                cur = Math.min(cur, afterAfter + cost[index]);
            }
            afterAfter = after;
            after = cur;
        }
        return Math.min(after, afterAfter);
    }
}
