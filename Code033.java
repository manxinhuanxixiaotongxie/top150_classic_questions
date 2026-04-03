/**
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * <p>
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 向左旋转，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,5,6,7] 下标 3 上向左旋转后可能变为 [4,5,6,7,0,1,2] 。
 * <p>
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 *
 */
public class Code033 {
    /**
     *
     * 找到nums的最小值的下标i，根据旋转数据的定义 下标在[0,i-1]之间的元素都比[i,n-1]中的元素大（题目保证没有重复元素）
     * 特殊情况：如果i等于0 那么nums就是严格递增数组 没有发生旋转
     * 根据这一性质：分类讨论：
     * 如果 target > nums[n-1] 那么target只能出现在[0,i-1]之间 由于子数组[0,i-1]是递增的 我们可以在[0,i-1]中二分查找target
     * 如果target <= nums[n-1] 那么target只可能出现在[i,n-1] 由于子数组[i,n-1]是递增的 我们可以在[i,n-1]中二分查找target
     * <p>
     * 注意上述讨论兼容i=0的情况
     * 如果target > nums[n-1] 由于nums是递增的 所以target比nums中的每个数都要大 所以nums不存在target 顺带一提 此时代码二分查找中的
     * nums[right] = target 相当于nums[0] == target 这一定是false
     * <p>
     * 如果target《= nums[n-1] 那么在[i,n-1]中二分 也就是在[0,n-1]中二分
     *
     * @param nums
     * @param target
     * @return
     */
    public int search1(int[] nums, int target) {
        // 找到最小值的下标
        int minIndex = getMin(nums);
        // 那么就有两种情况
        // 第一种情况i ==0 的时候 整个数组没有发生旋转 依然保持递增
        // 第二种情况 整个数组被拆分成两段 每一段都是单调递增 并且左边的那段比右边的那段的每个数都要大
        // 我们在在这两段中进行二分查找target
        int index = getIndex(nums, target, 0, minIndex - 1);
        // 增加 index < minIndex 的判断，确保在范围内
        if (index < minIndex && nums[index] == target) {
            return index;
        }

        int index2 = getIndex(nums, target, minIndex, nums.length - 1);
        // 增加 index2 < nums.length 的判断
        if (index2 < nums.length && nums[index2] == target) {
            return index2;
        }
        return -1;
    }

    public int getIndex(int[] nums, int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;          // 直接返回真实下标
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // code153:在旋转数据中找到最小数字下标
    public int getMin(int[] nums) {
        // 在一个数组中找到最小值的下标
        int left = 0;
        int n = nums.length;
        int right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[n - 1]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
