import javax.security.auth.login.AccountNotFoundException;

/**
 * 环形子数组的最大和
 */
public class Code918_MaxSubarraySumCircular {
    /**
     * 无法完全AC  超出时间复杂度限制
     *
     * @param nums
     * @return
     */
    public int maxSubarraySumCircular(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        // 将数组加工一下 加工成两倍长度
        int N = nums.length;
        int[] arr = new int[N << 1];
        for (int i = 0; i < N; i++) {
            arr[i] = nums[i];
            arr[i + N] = nums[i];
        }
        // 数组加工成两倍长度
        N = arr.length;
        int index = N - 1;
        int ans = Integer.MIN_VALUE;
        while (index > nums.length) {
            // 从index往前推nums.length -1个数
            int temp = index - nums.length + 1;
            int pre = arr[index];
            int cur = index - 1;
            while (cur >= temp) {
                if (pre > 0) {
                    pre += arr[cur];
                } else {
                    pre = arr[cur];
                }
                ans = Math.max(ans, pre);
                cur--;
            }

            index--;
        }
        return ans;
    }

    public static void main(String[] args) {
        Code918_MaxSubarraySumCircular code918 = new Code918_MaxSubarraySumCircular();
        int[] nums = {-2};
        System.out.println(code918.maxSubarraySumCircular(nums));
    }

}
