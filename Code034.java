/**
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * <p>
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 *
 */
public class Code034 {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        // 找开始位置
        int[] ans = new int[]{-1, -1};
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (left == nums.length || nums[left] != target) {
            return ans;
        }
        ans[0] = left;
        left = 0;
        right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= (target + 1)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        ans[1] = left - 1;
        return ans;
    }

    public int[] searchRange2(int[] nums, int target) {
        int index = findeIndex(nums, target);
        if (index == nums.length || nums[index] != target) {
            return new int[]{-1, -1};
        }
        int[] ans = new int[]{index, -1};
        index = findeIndex(nums, target + 1);
        ans[1] = index - 1;
        return ans;
    }

    public int findeIndex(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
