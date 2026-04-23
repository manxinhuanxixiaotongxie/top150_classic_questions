package leetcode75;

/**
 * 最大连续1的个数
 * <p>
 * 给定一个二进制数组 nums 和一个整数 k，假设最多可以翻转 k 个 0 ，则返回执行操作后 数组中连续 1 的最大个数 。
 */
public class Code1004 {
    public int longestOnes(int[] nums, int k) {
        // 窗口范围内0的数量
        int count0 = 0;
        int right = 0;
        int ans = 0;
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            while (right < N && count0 <= k) {
                // 如果窗口已经是k了
                if (count0 == k && nums[right] == 0) {
                    break;
                }
                if (nums[right] == 0) count0++;
                right++;
            }
            ans = Math.max(ans, right - i);
            if (nums[i] == 0) {
                count0--;
            }
        }
        return ans;
    }

    /**
     * 滑动窗口
     *
     * @param nums
     * @param k
     * @return
     */
    public int longestOnes2(int[] nums, int k) {
        // 维持一个窗口 让窗口内的0的数量小于等于K 那么窗口的长度就是满足条件连续1的个数
        int ans = 0;
        // 0的个数
        int count = 0;
        int end = 0;
        for (int i = 0; i < nums.length; i++) {
            while (end < nums.length && count <= k) {
                if (nums[end] == 0) {
                    count++;
                }
                end++;
            }
            // 结算：count > k 说明 end 多走了一步，需要减1
            ans = Math.max(ans, count > k ? end - i - 1 : end - i);
            if (end == nums.length) {
                break;
            }
            if (nums[i] == 0) {
                count--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Code1004 code1004 = new Code1004();
        int[] nums = {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        int k = 3;
        int result = code1004.longestOnes(nums, k);
        System.out.println("最长连续1的个数: " + result);
        int result2 = code1004.longestOnes2(nums, k);
        System.out.println("最长连续1的个数2：" + result2);
    }
}
