import java.util.Arrays;

/**
 * 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个在 不同下标位置 的整数，
 * 使它们的和与 target 最接近。
 * <p>
 * 返回这三个数的和。
 * <p>
 * 假定每组输入只存在恰好一个解。
 *
 *
 */
public class Code016 {
    /**
     * 思路与三数之和类似
     * 1.排序
     * 2.枚举nums[i]作为第一个数  问题变成找到另外两个数 使得这三个数的和与target最接近 同样使用双指针解决
     * 假设s = nums[i] + nums[j] +nums[k]
     * 如果|s - target| < |ans - target| 那么更新ans = s ans初始值为∞
     * 分类讨论：
     * 1.s== target 返回s
     * 2.s > target 那么与三数之和一样 把K减一
     * 3.s < target 那么把J加一
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) return 0;
        // 计算差值
        Arrays.sort(nums);
        int n = nums.length;
        // 计算绝对值差值 防止整数溢出
        int ans = Integer.MAX_VALUE / 2;
        for (int i = 0; i < n - 2; i++) {
            int x = nums[i];
            // 优化
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int s = x + nums[i + 1] + nums[i + 2];
            // 优化 最小的三个数
            if (s > target) {
                if (s - target < Math.abs(ans - target)) {
                    ans = s;
                }
                break;
            }
            s = x + nums[n - 1] + nums[n - 2];
            // 优化 最大的三个数
            if (s < target) {
                if (target - s < Math.abs(ans - target)) {
                    ans = s;
                }
                continue;
            }
            int j = i + 1, k = n - 1;
            while (j < k) {
                s = x + nums[j] + nums[k];
                if (s == target) {
                    return target;
                }
                // 更新逻辑 绝对值
                if (Math.abs(s - target) < Math.abs(ans - target)) {
                    ans = s;
                }
                if (s > target) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return ans;
    }
}
