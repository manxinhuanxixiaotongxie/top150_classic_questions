package leetcode75;

/**
 * 给你一个二进制数组 nums ，你需要从中删掉一个元素。
 * <p>
 * 请你在删掉元素的结果数组中，返回最长的且只包含 1 的非空子数组的长度。
 * <p>
 * 如果不存在这样的子数组，请返回 0 。
 */
public class Code1493 {
    public int longestSubarray(int[] nums) {
        int count0 = 0; // 记录0的个数
        int right = 0;
        int ans = 0;
        int N = nums.length;
        for (int i = 0; i < nums.length; i++) {
            while (right < N && count0 <= 1) {
                if (count0 == 1 && nums[right] == 0) break;
                if (nums[right] == 0) count0++;
                right++;
            }
            ans = Math.max(ans, right - i - 1);
            if (nums[i] == 0) {
                count0--;
            }
        }
        return ans;
    }
}
