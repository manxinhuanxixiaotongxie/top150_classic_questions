package leetcode75;

public class Code334 {

    /**
     * 暴力解法 超时
     *
     * @param nums
     * @return
     */
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        return process(nums, 0, Integer.MIN_VALUE, 3);
    }

    public boolean process(int[] nums, int index, int pre, int rest) {
        if (index == nums.length) {
            return rest <= 0;
        } else {
            boolean ans = false;
            ans |= process(nums, index + 1, pre, rest);
            if (nums[index] > pre && rest > 0) {
                ans |= process(nums, index + 1, nums[index], rest - 1);
            }
            return ans;
        }
    }

    public boolean increasingTriplet2(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        int first = nums[0], second = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > second) {
                return true;
            } else if (nums[i] > first) {
                second = nums[i];
            } else {
                first = nums[i];
            }
        }
        return false;
    }

}
