/**
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * <p>
 * 找出该数组中满足其总和大于等于 target 的长度最小的 子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，
 * 并返回其长度。如果不存在符合条件的子数组，返回 0 。
 */
public class Code209_MinSubArrayLen {
    /**
     * 都是正整数  有单调性 滑动窗口
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int res = Integer.MAX_VALUE;
        int end = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            while (sum < target) {
                if (end == nums.length) {
                    break;
                }
                sum += nums[end];
                end++;
            }
            // 结算
            if (sum >= target) {
                res = Math.min(res, end - i);
            }
            // 先减去当前的
            sum -= nums[i];
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
