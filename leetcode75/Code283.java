package leetcode75;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 *
 */
public class Code283 {

    // 原地算法 双指针
    public void moveZeroes(int[] nums) {
        int index = 0;
        int left = 0;
        while (index < nums.length) {
            if (nums[index] != 0) {
                swap(nums, index++, left++);
            } else {
                // 是0
                index++;
            }
        }
    }

    public void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}
