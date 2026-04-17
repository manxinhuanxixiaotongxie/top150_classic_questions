/**
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除了 nums[i] 之外其余各元素的乘积 。
 * <p>
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 * <p>
 * 请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * <p>
 * 进阶：你可以在 O(1) 的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组 不被视为 额外空间。）
 *
 */
public class Code238 {
    public int[] productExceptSelf(int[] nums) {
        int[] ans = new int[nums.length];
        // 不包含当前位置 后面的乘积
        int right = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            ans[i] = right;
            right *= nums[i];
        }
        int left = 1;
        for (int i = 0; i < nums.length; i++) {
            ans[i] = left * ans[i];
            left *= nums[i];
        }
        return ans;
    }
}
