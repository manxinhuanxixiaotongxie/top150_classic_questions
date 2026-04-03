/**
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组
 * （子数组最少包含一个元素），返回其最大和。
 *
 */
public class Code053 {
    public int maxSubArray(int[] nums) {
        // 以当前位置开头 能取到的最大值
        int N = nums.length;
        int max = nums[N - 1];
        int next = max;
        for (int i = N - 2; i >= 0; i--) {
            if (next > 0) {
                max = Math.max(max, next + nums[i]);
                next += nums[i];
            } else {
                max = Math.max(max, nums[i]);
                next = nums[i];
            }
        }
        return max;
    }
}
