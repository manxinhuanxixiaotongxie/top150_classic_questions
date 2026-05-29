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
        // ===================== 核心思想 =====================
        // 贪心 + 二分查找，时间复杂度 O(n log n)
        //
        // 变量含义：索引j代表长度
        // 值g[j]代表：所有长度为j+1的递增子序列中，结尾数字最小值
        // 例如：g[0] = 2 含义是 最长递增子序列的长度为1的时候 结尾数字最小是2
        // g是严格递增的(天然单调性)
        //
        // 贪心策略：对于同样长度的递增子序列，结尾数字越小越好！
        //           结尾数字越小，后续越容易接上更大的数，形成更长的子序列。
        // =====================================================

        // g 就是我们维护的"贪心数组"
        // g 不一定是原数组的真实子序列，但 g.size() 一定等于最长递增子序列的长度
        // 示例：nums = [10, 9, 2, 5, 3, 7, 101, 18]
        // 处理完后 g = [2, 3, 7, 18]，长度4就是答案
        List<Integer> g = new ArrayList<>();

        for (int x : nums) {
            // -------------------------------------------------------
            // 二分查找：在 g 中寻找第一个 >= x 的位置 j

            /**
             * 查找下标j的含义
             * （1）x能不能让现在的序列变的更长
             * （2）
             */

            //
            // 为什么找 >= x（而不是 > x）？
            //   因为题目要求"严格递增"，相等的数字不能接在后面，
            //   所以找到第一个 >= x 的位置，用 x 替换它。
            //   如果找的是 > x，则相等的数也会被追加，变成非严格递增。
            // -------------------------------------------------------
            int j = lowerBound2(g, x);

            if (j == g.size()) {
                // -------------------------------------------------------
                // 情况1：g 中所有数都 < x（找不到 >= x 的位置）
                //        说明 x 比 g 中所有数都大，
                //        可以直接拼接在当前最长子序列的末尾，形成更长的子序列！
                //        → 追加 x，g 长度 +1，答案候选长度增加。
                //
                // 例：g = [2, 3, 7]，x = 18
                //     18 > 7，j = 3 = g.size()，直接追加
                //     g → [2, 3, 7, 18]
                // -------------------------------------------------------
                g.add(x); //
            } else {
                // -------------------------------------------------------
                // 情况2：g 中存在第一个 >= x 的位置 j
                //        用 x 替换 g[j]。
                //
                // 为什么可以替换？
                //   g[j] 是"长度为 j+1 的子序列中，当前记录的最小结尾"。
                //   现在找到了一个更小（或相等）的 x，
                //   用 x 替换后，同样长度的子序列结尾变得更小，
                //   未来接上更大数字的可能性更大 → 贪心最优。
                //
                // 注意：替换操作不会改变 g 的长度，只是更新了对应长度的最优结尾。
                //
                // 例：g = [2, 5]，x = 3
                //     第一个 >= 3 的位置是 j=1（g[1]=5 >= 3）
                //     用 3 替换 5：g → [2, 3]
                //     含义：长度为2的子序列，最小结尾从5变成了3，更优！
                //
                // 再例：g = [2, 3, 7]，x = 5
                //     第一个 >= 5 的位置是 j=2（g[2]=7 >= 5）
                //     用 5 替换 7：g → [2, 3, 5]
                //     含义：长度为3的子序列，最小结尾从7变成了5，更优！

                /**
                 * "虽然我现在不能让楼盖得更高，但我能把第 j+1 层的地板换成更薄、更轻的材料（更小的 $x$），这样以后我就能更容易地往上加盖。"
                 */
                g.set(j, x);
            }
        }

        // g.size() 就是最长递增子序列的长度
        // （g 本身不一定是真实的递增子序列，但长度一定正确）
        //
        // 完整示例演示（nums = [10, 9, 2, 5, 3, 7, 101, 18]）：
        // 处理 10 → g=[]，j=0=size，追加    → g=[10]
        // 处理  9 → g=[10]，j=0(10>=9)，替换 → g=[9]
        // 处理  2 → g=[9]，j=0(9>=2)，替换  → g=[2]
        // 处理  5 → g=[2]，j=1=size，追加   → g=[2,5]
        // 处理  3 → g=[2,5]，j=1(5>=3)，替换 → g=[2,3]
        // 处理  7 → g=[2,3]，j=2=size，追加  → g=[2,3,7]
        // 处理101 → g=[2,3,7]，j=3=size，追加 → g=[2,3,7,101]
        // 处理 18 → g=[2,3,7,101]，j=3(101>=18)，替换 → g=[2,3,7,18]
        // 最终 g.size()=4，答案为 4
        return g.size();
    }

    /**
     * 方法5的原地算法
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS7(int[] nums) {
        // g 的长度
        int ng = 0;
        for (int x : nums) {
            int j = lowerBound3(nums, ng, x);
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
