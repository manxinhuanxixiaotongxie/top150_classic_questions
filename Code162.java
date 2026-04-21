/**
 * 峰值元素是指其值严格大于左右相邻值的元素。
 * <p>
 * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * <p>
 * 你可以假设 nums[-1] = nums[n] = -∞ 。
 * <p>
 * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 *
 */
public class Code162 {
    /**
     * 性质分析：
     *
     * 如果i< n-1 && nums[i] < nums[i+1] 那么在下标[i+1,n-1]中一定存在峰值
     *
     * 反证法：如果不存在峰值 因为不存在峰值 那么nums[i+1]也不是峰值
     *
     * 为什么相等也往右走：题目已经给定了相邻不会相等
     *
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return 0;
        if (nums[0] > nums[1]) return 0;
        if (nums[nums.length - 1] > nums[nums.length - 2]) return nums.length - 1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
