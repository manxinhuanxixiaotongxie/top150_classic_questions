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

    public int longestSubarray2(int[] nums) {
        int count0 = 0; // 记录0的个数
        int end = 0;
        int ans = 0;
        // 滑动窗口 窗口内最多维持0的数量为1
        for (int i = 0; i < nums.length; i++) {
            while (end < nums.length && count0 <= 1) {
                if (nums[end] == 0) {
                    count0++;
                }
                end++;
            }
            // 结算：必须删除一个元素，所以窗口长度要-1；count0>1时end多走了一步再-1
            ans = Math.max(ans, count0 > 1 ? end - i - 2 : end - i - 1);
            if (nums[i] == 0) {
                count0--;
            }
        }
        return ans;
    }
}
