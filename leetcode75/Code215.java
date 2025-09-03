package leetcode75;

/**
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * <p>
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 */
public class Code215 {
    // 题目要求时间复杂度O（N）可以实现
    // 维护一个大小为K的小根堆 时间复杂度是O(N*logK) 其中k 是常数 时间复杂度也是O（N）
    // 改写快排
    public int findKthLargest(int[] nums, int k) {
        return process(nums, 0, nums.length - 1, k - 1);
    }

    public int process(int[] nums, int l, int r, int k) {
        if (l >= r) {
            return nums[l];
        }
        int[] partition = partition(nums, l, r);
        if (k >= partition[0] && k <= partition[1]) {
            return nums[k];
        } else if (k < partition[0]) {
            return process(nums, l, partition[0] - 1, k);
        } else {
            return process(nums, partition[1] + 1, r, k);
        }
    }

    public int[] partition(int[] nums, int l, int r) {
        // 将数组划分成左边大、右边小的结构
        int left = l - 1;
        int right = r + 1;
        int i = l;
        int index = l + (int) (Math.random() * ( r- l + 1));
        int val = nums[index];
        while (i < right) {
            if (nums[i] == val) {
                i++;
            } else if (nums[i] < val) {
                swap(nums, i, --right);
            } else {
                swap(nums, i++, ++left);
            }
        }
        return new int[]{left + 1, right - 1};
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
