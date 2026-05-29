/**
 * 给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，
 * 返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。
 *
 */
public class Code026 {
    /**
     * 双指针
     * <p>
     * left 语义：去重区间的"右边界，包含"——
     * 已去重区间为 nums[0..left]（两端均闭），left 本身就是区间内最后一个元素的下标；
     * left = -1 是哨兵，表示区间为空（尚未写入任何元素）；
     * 下一个待写入位置 = left + 1（写入后再把 left 自增到该位置）。
     * <p>
     * 原代码 3 个 bug：
     * 1. 比较写成 nums[left+1]：left+1 是"尚未写入的下一个位置"，里面还是旧数据，
     * 等于在和自己/脏数据比，逻辑错误。应该和区间内最后一个元素 nums[left] 比。
     * 2. left=-1 时 nums[left]=nums[-1] 会越界，需要单独判空（用 left == -1 短路）。
     * 3. 返回值应是长度 left+1，而不是 left。
     * <p>
     * 反例 nums=[1,1,2]：原代码返回 0 且数组被打乱为 [2,1,1]。
     *
     * @param nums
     * @return 去重后的长度
     */
    public int removeDuplicates(int[] nums) {
        // 不重复的右边界（包含）：left 指向区间内最后一个元素；-1 表示空区间
        int left = -1;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (left == -1 || nums[i] != nums[left]) {
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
