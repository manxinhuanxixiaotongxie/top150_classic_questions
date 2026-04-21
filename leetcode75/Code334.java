package leetcode75;

/**
 *
 * 给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。
 * <p>
 * 如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回 true ；
 * 否则，返回 false 。
 * <p>
 * 进阶：你能实现时间复杂度为 O(n) ，空间复杂度为 O(1) 的解决方案吗？
 *
 */
public class Code334 {

    /**
     * 暴力解法 超时
     * <p>
     * 进阶：你能实现时间复杂度为 O(n) ，空间复杂度为 O(1) 的解决方案吗？
     *
     * @param nums
     * @return
     */
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        return process(nums, 0, Integer.MIN_VALUE, 3);
    }

    public boolean process(int[] nums, int index, int pre, int rest) {
        if (index == nums.length) {
            return rest <= 0;
        } else {
            boolean ans = false;
            ans |= process(nums, index + 1, pre, rest);
            if (nums[index] > pre && rest > 0) {
                ans |= process(nums, index + 1, nums[index], rest - 1);
            }
            return ans;
        }
    }

    /**
     * 可以参考leetcode300 最长递增子序列
     *
     * @param nums
     * @return
     */
    public boolean increasingTriplet2(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        int first = nums[0], second = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > second) {
                return true;
            } else if (nums[i] > first) {
                second = nums[i];
            } else {
                first = nums[i];
            }
        }
        return false;
    }

}
