/**
 * 定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * <p>
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 */
public class Code215 {
    public int findKthLargest(int[] nums, int k) {
        return process(nums, 0, nums.length - 1, k - 1);
    }

    // 改写快排 第K大 就是从大到小排序之后 应该占据k-1位置的数
    public int process(int[] nums, int l, int r, int k) {

        int[] partition = partition(nums, l, r);
        if (k >= partition[0] && k <= partition[1]) {
            return nums[k];
        } else if (k < partition[0]) {
            // 在左边
            return process(nums, l, partition[0] - 1, k);
        } else {
            // 在右边
            return process(nums, partition[1] + 1, r, k);
        }
    }

    public int[] partition(int[] nums, int l, int r) {
        // 在l 与 r范围上进行partition动作
        // 将数组划分成左边大 中间相等 右边小的形式
        int left = l;
        int right = r;
        int value = nums[l + (int) (Math.random() * (r - l + 1))];
        for (int i = l; i <= right; ) {
            if (nums[i] > value) {
                swap(nums, i++, left++);
            } else if (nums[i] == value) {
                i++;
            } else {
                swap(nums, i, right--);
            }
        }
        return new int[]{left, right};
    }

    public void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}
