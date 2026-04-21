package leetcode75;

public class Code238 {
    public int[] productExceptSelf(int[] nums) {
        int mulLeft = 1;
        int[] help = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            help[i] = mulLeft;
            mulLeft *= nums[i];
        }
        mulLeft = 1;
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = mulLeft * help[i];
            mulLeft *= nums[i];
        }
        return ans;
    }

    /**
     * 空间O(1)的做法
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf2(int[] nums) {
        int[] ans = new int[nums.length];
        int n = nums.length;
        int pre = 1;
        for (int i = n - 1; i >= 0; i--) {
            ans[i] = pre;
            pre *= nums[i];
        }
        int left = 1;
        for (int i = 0; i < n; i++) {
            ans[i] = left * ans[i];
            left *= nums[i];
        }
        return ans;
    }
}
