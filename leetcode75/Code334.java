package leetcode75;

import java.util.ArrayList;
import java.util.List;

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
     * 方法1不是的一个好尝试
     *
     * @param nums
     * @return
     */
    public boolean increasingTriplet2(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        return process(nums, 0, Integer.MIN_VALUE, 3);
    }

    /**
     * 超时
     *
     * @param nums
     * @return
     */
    public boolean increasingTriplet3(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        for (int i = 0; i < nums.length - 2; i++) {
            if (process(nums, i) >= 3) {
                return true;
            }
        }
        return false;
    }

    public int process(int[] nums, int index) {
        // 递归函数含义 必须以当前位置结尾 最长递增子序列的长度
        if (index == nums.length) {
            return 0;
        } else {
            int max = 1;
            for (int i = index + 1; i < nums.length; i++) {
                if (nums[i] > nums[index]) {
                    max = Math.max(process(nums, i) + 1, max);
                }
            }
            return max;
        }
    }

    /**
     * 将方法3改成动态规划
     *
     * @param nums
     * @return
     */
    public boolean increasingTriplet4(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        int n = nums.length;
        int[] dp = new int[n + 1];
        for (int index = n - 1; index >= 0; index--) {
            dp[index] = 1;
            for (int i = index + 1; i < nums.length; i++) {
                if (nums[i] > nums[index]) {
                    dp[index] = Math.max(dp[i] + 1, dp[index]);
                }
            }
            if (dp[index] >= 3) {
                return true;
            }
        }
        return false;
    }

    /**
     * 使用LIS进行改写
     *
     * @param nums
     * @return
     */
    public boolean increasingTriplet5(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        List<Integer> g = new ArrayList<>();
        for (int num : nums) {
            // 当前数字
            int upper = findUpper(g, num);
            if (upper == g.size()) {
                // 没有任何数字比它大
                g.add(num);
            }else {
                // 当前位置是大于等于num的位置 意味着当前位置能够取到的长度 一定可以更新 并且是最小值
                g.set(upper, num);
            }
        }
        return g.size() >= 3;
    }

    public int findUpper(List<Integer> nums, int target) {
        // 大于等于target
        int left = 0;
        int right = nums.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    /**
     * 官解
     *
     * @param nums
     * @return
     */
    public boolean increasingTriplet6(int[] nums) {
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
