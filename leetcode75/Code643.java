package leetcode75;

/**
 * 给你一个由 n 个元素组成的整数数组 nums 和一个整数 k 。
 * <p>
 * 请你找出平均数最大且 长度为 k 的连续子数组，并输出该最大平均数。
 * <p>
 * 任何误差小于 10-5 的答案都将被视为正确答案。
 */
public class Code643 {
    /**
     * 第一种解法 滑动窗口
     * 维持一个长度为K的窗口
     *
     * @param nums
     * @param k
     * @return
     */
    public double findMaxAverage1(int[] nums, int k) {

        // 平均数最大 长度为k的连续子数组
        // 数组有正、有负、有零
        double ans = Integer.MIN_VALUE;


        int N = nums.length;
        long sum = 0;
        for (int i = 0; i < N; i++) {
            sum += nums[i]; // 将字符转换为数字
            if (i < k - 1) {
                continue;
            }
            ans = Math.max(ans,  sum * 1.0 / k); // 计算平均数
            sum -= nums[i - k + 1]; // 移除窗口左侧的元素
        }

        return ans;
    }

}
