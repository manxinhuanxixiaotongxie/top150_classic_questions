/**
 * 给你一个满足下述两条属性的 m x n 整数矩阵：
 * <p>
 * 每行中的整数从左到右按非严格递增顺序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
 *
 */
public class Code074 {
    public boolean searchMatrix(int[][] matrix, int target) {
        // 在第一列进行查找
        int row = matrix.length;
        int r = 0;
        while (r < row && matrix[r][0] <= target) {
            r++;
        }
        // 此时r来到了大于target的一行 如果存在的话 只有可能在在r-1行 不然是不可能存在的
        if (r == 0) {
            // 第一个数就比target要大 不可能存在
            return false;
        } else {
            r--;
            // 在r-1进行二分查找
            int[] nums = matrix[r];
            return findInNums(nums, target);
        }
    }

    public boolean findInNums(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left < nums.length && nums[left] == target;
    }
}
