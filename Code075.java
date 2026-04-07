/**
 * 颜色分类
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地 对它们进行排序，
 * 使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 必须在不使用库内置的 sort 函数的情况下解决这个问题。
 * 其实是一个荷兰国旗的问题的划分
 *
 */
public class Code075 {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) return;
        // 0 红色
        // 1 白色
        // 2 蓝色
        int left = 0;
        int right = nums.length - 1;
        // 左边界跟有边界 相等 不包含
        int index = 0;
        while (index <= right) {
            // 红色
            if (nums[index] == 0) {
                swap(nums, left++, index++);
            } else if (nums[index] == 1) {
                // 白色
                index++;
            } else {
                // 蓝色
                swap(nums, right--, index);
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
