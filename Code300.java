import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * <p>
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 2500
 * -10^4 <= nums[i] <= 10^4
 * <p>
 * 进阶：
 * <p>
 * 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
 *
 */
public class Code300 {
    /**
     * 根据题目给的数据状态 这不是一个好的尝试
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return process(nums, 0, Integer.MIN_VALUE);
    }

    public int process(int[] nums, int index, int pre) {
        if (index == nums.length) {
            return 0;
        }
        // 当前位置不要
        int p1 = process(nums, index + 1, pre);
        // 当前位置要
        // 有条件的要
        int p2 = 0;
        if (nums[index] > pre) {
            p2 = process(nums, index + 1, nums[index]) + 1;
        }
        return Math.max(p1, p2);
    }

    public int lengthOfLIS2(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans = Math.max(ans, process2(nums, i));
        }
        return ans;
    }

    public int process2(int[] nums, int index) {
        int ans = 1;
        for (int i = index + 1; i < nums.length; i++) {
            if (nums[i] > nums[index]) {
                ans = Math.max(ans, process2(nums, i) + 1);
            }
        }
        return ans;
    }

    public int lengthOfLIS3(int[] nums) {
        // 改动态规划
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n];
        dp[n - 1] = 1;
        for (int index = n - 2; index >= 0; index--) {
            dp[index] = 1;
            for (int i = index + 1; i < n; i++) {
                if (nums[i] > nums[index]) {
                    dp[index] = Math.max(dp[index], dp[i] + 1);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public int lengthOfLIS4(int[] nums) {
        // 改动态规划
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n];
        dp[n - 1] = 1;
        int ans = 1;
        for (int index = n - 2; index >= 0; index--) {
            dp[index] = 1;
            for (int i = index + 1; i < n; i++) {
                if (nums[i] > nums[index]) {
                    dp[index] = Math.max(dp[index], dp[i] + 1);
                }
            }
            ans = Math.max(ans, dp[index]);
        }
        return ans;
    }

    public int lengthOfLIS5(int[] nums) {
        // 变量含义：索引j代表长度
        // 值g[j]代表：所有长度为j+1的递增子序列中，结尾数字最小值
        // g是严格递增的(天然单调性)

        /**
         * 记住 g[i] 的含义：长度为 $i+1$ 的递增子序列中，最小的结尾数字。假设我们要证明 $g[i-1] < g[i]$（即长度为 $k$ 的结尾一定小于长度为 $k+1$ 的结尾）：
         * 对于任何一个长度为 $i+1$ 的递增子序列，它的最后一个数字是 $x$。既然它是递增的，那么在 $x$ 之前，一定存在一个长度为 $i$ 的递增子序列。
         * 假设这个长度为 $i$ 的序列结尾数字是 $y$，由于序列递增，必然有 $y < x$。根据定义，$g[i-1]$ 是所有长度为 $i$ 的序列结尾中最小的一个，
         * 所以 $g[i-1] \le y$。综合得到：$g[i-1] \le y < x$。
         * 既然对于任意长度为 $i+1$ 序列的结尾 $x$，都有 $g[i-1] < x$，那么作为这些 $x$ 中最小值的 $g[i]$，自然也必须满足 $g[i-1] < g[i]$。
         */
        // g[0] = 2 含义是 最长递增子序列的长度为1的时候 取得长度为1的最小值是2
        List<Integer> g = new ArrayList<>();
        for (int x : nums) {
            // 二分查找 在g中寻找第一个大于或者等于当前数字x的位置
            int j = lowerBound2(g, x);
            if (j == g.size()) {
                //>=x 的 g[j] 不存在 意味着 当前数字x比g中所有的数字都大 也就是当前数字x可以接在g中所有的序列后面 形成一个更长的递增子序列
                g.add(x); //
            } else {
                /**
                 * “虽然我现在不能让楼盖得更高，但我能把第 $j+1$ 层的地板换成更薄、更轻的材料（更小的 $x$），这样以后我就能更容易地往上加盖。”
                 *
                 */
                g.set(j, x);
            }
        }
        return g.size();
    }

    /**
     * 方法5的原地算法
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS7(int[] nums) {
        int ng = 0; // g 的长度
        for (int x : nums) {
            int j = lowerBound3(nums, ng, x);
//            if (j == ng) {
////                nums[ng++] = x;
//                nums[j] = x;
//                ng++;
//            }else {
//                nums[j] = x;
//            }
            nums[j] = x;
            if (j == ng) { // >=x 的 g[j] 不存在
                ng++;
            }
        }
        return ng;
    }

    // 开区间写法
    private int lowerBound3(int[] nums, int right, int target) {
        int left = -1; // 开区间 (left, right)
        while (left + 1 < right) { // 区间不为空
            // 循环不变量：
            // nums[left] < target
            // nums[right] >= target
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid; // 范围缩小到 (mid, right)
            } else {
                right = mid; // 范围缩小到 (left, mid)
            }
        }
        return right; // 或者 left+1
    }



    private int lowerBound2(List<Integer> g, int target) {
        int left = 0;
        int right = g.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (g.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    /**
     * 最长递增子序列的最优写法： 贪心 + 二分查找
     *
     * <p>
     * 用更小的数字替换掉同样长度序列的末尾，为未来的递增留出更多空间
     *
     * @param nums
     * @return
     */
    public boolean increasingTriplet6(int[] nums) {
        // 变量含义：索引j代表长度
        // g[j]代表：所有长度为j+1的递增子序列中，结尾数字的最小值
        // g是严格递增的
        List<Integer> g = new ArrayList<>();
        for (int x : nums) {
            int j = lowerBound(g, x);
            if (j == 2) { // LIS 长度至少是 3
                return true;
            }
            if (j == g.size()) { // >=x 的 g[j] 不存在
                g.add(x);
            } else {
                g.set(j, x);
            }
        }
        return false;
    }

    // lowerBound二分函数 在g中找寻第一个 >= target 的位置
    private int lowerBound(List<Integer> g, int target) {
        int left = -1, right = g.size(); // 开区间 (left, right)
        while (left + 1 < right) { // 区间不为空
            // 循环不变量：
            // nums[left] < target
            // nums[right] >= target
            int mid = left + (right - left) / 2;
            if (g.get(mid) < target) {
                left = mid; // 范围缩小到 (mid, right)
            } else {
                right = mid; // 范围缩小到 (left, mid)
            }
        }
        return right; // 或者 left+1
    }
}
