/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * <p>
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 2500
 * -10^4 <= nums[i] <= 10^4
 * <p>
 * 进阶：
 * <p>
 * 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
 *
 */
public class Code300 {
    /**
     * 根据题目给的数据状态 这不是一个好的尝试
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return process(nums, 0, Integer.MIN_VALUE);
    }

    public int process(int[] nums, int index, int pre) {
        if (index == nums.length) {
            return 0;
        }
        // 当前位置不要
        int p1 = process(nums, index + 1, pre);
        // 当前位置要
        // 有条件的要
        int p2 = 0;
        if (nums[index] > pre) {
            p2 = process(nums, index + 1, nums[index]) + 1;
        }
        return Math.max(p1, p2);
    }

    public int lengthOfLIS2(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans = Math.max(ans, process2(nums, i));
        }
        return ans;
    }

    public int process2(int[] nums, int index) {
        int ans = 1;
        for (int i = index + 1; i < nums.length; i++) {
            if (nums[i] > nums[index]) {
                ans = Math.max(ans, process2(nums, i) + 1);
            }
        }
        return ans;
    }

    public int lengthOfLIS3(int[] nums) {
        // 改动态规划
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n];
        dp[n - 1] = 1;
        for (int index = n - 2; index >= 0; index--) {
            dp[index] = 1;
            for (int i = index + 1; i < n; i++) {
                if (nums[i] > nums[index]) {
                    dp[index] = Math.max(dp[index], dp[i] + 1);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public int lengthOfLIS4(int[] nums) {
        // 改动态规划
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n];
        dp[n - 1] = 1;
        int ans = 1;
        for (int index = n - 2; index >= 0; index--) {
            dp[index] = 1;
            for (int i = index + 1; i < n; i++) {
                if (nums[i] > nums[index]) {
                    dp[index] = Math.max(dp[index], dp[i] + 1);
                }
            }
            ans = Math.max(ans, dp[index]);
        }
        return ans;
    }
}
