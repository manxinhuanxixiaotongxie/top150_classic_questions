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

    /**
     * 官解
     *
     * @param nums
     * @return
     */
    public int maxSubarraySumCircular2(int[] nums) {
        int n = nums.length;
        int[] leftMax = new int[n];
        // 对坐标为 0 处的元素单独处理，避免考虑子数组为空的情况
        leftMax[0] = nums[0];
        int leftSum = nums[0];
        int pre = nums[0];
        int res = nums[0];
        for (int i = 1; i < n; i++) {
            pre = Math.max(pre + nums[i], nums[i]);
            res = Math.max(res, pre);
            leftSum += nums[i];
            leftMax[i] = Math.max(leftMax[i - 1], leftSum);
        }

        // 从右到左枚举后缀，固定后缀，选择最大前缀
        int rightSum = 0;
        for (int i = n - 1; i > 0; i--) {
            rightSum += nums[i];
            res = Math.max(res, rightSum + leftMax[i - 1]);
        }
        return res;
    }

    public static void main(String[] args) {
        Code918_MaxSubarraySumCircular code918 = new Code918_MaxSubarraySumCircular();
        int[] nums = {-2};
        System.out.println(code918.maxSubarraySumCircular(nums));
    }

}
