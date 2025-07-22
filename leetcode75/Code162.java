package leetcode75;

/**
 * 峰值元素是指其值严格大于左右相邻值的元素。
 * <p>
 * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * <p>
 * 你可以假设 nums[-1] = nums[n] = -∞ 。
 * <p>
 * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 */
public class Code162 {
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return 0;
        if (nums[0] > nums[1]) return 0;
        if (nums[nums.length - 1] > nums[nums.length - 2]) return nums.length - 1;
        // 只需要找一个峰值就可以
        int left = -1;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid ;
            }else {
                left = mid;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        Code162 code162 = new Code162();
        int[] nums = {3, 4, 3, 2, 1};
        int peakIndex = code162.findPeakElement(nums);
        System.out.println("Peak index: " + peakIndex); // 输出: Peak index: 2
    }
}
