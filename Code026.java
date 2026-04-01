/**
 * 给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，
 * 返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。
 *
 */
public class Code026 {
    /**
     * 双指针
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int left = 0;
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            if (nums[i] != nums[left]) {
                swap(nums, ++left, i);
            }
        }
        return left + 1;
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
