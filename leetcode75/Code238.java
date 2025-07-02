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
}
