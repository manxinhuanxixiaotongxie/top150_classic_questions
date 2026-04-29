package leetcode75;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，两者长度都是 n ，再给你一个正整数 k 。你必须从 nums1 中选一个长度为 k 的 子序列 对应的下标。
 * <p>
 * 对于选择的下标 i0 ，i1 ，...， ik - 1 ，你的 分数 定义如下：
 * <p>
 * nums1 中下标对应元素求和，乘以 nums2 中下标对应元素的 最小值 。
 * 用公式表示： (nums1[i0] + nums1[i1] +...+ nums1[ik - 1]) * min(nums2[i0] , nums2[i1], ... ,nums2[ik - 1]) 。
 * 请你返回 最大 可能的分数。
 * <p>
 * 一个数组的 子序列 下标是集合 {0, 1, ..., n-1} 中删除若干元素得到的剩余集合，也可以不删除任何元素。
 *
 * 提示：
 *
 * n == nums1.length == nums2.length
 * 1 <= n <= 10^5
 * 0 <= nums1[i], nums2[j] <= 10^5
 * 1 <= k <= n
 *
 */
public class Code2542 {
    /**
     * 暴力解法
     * 暴力枚举数组1的所有长度为K的子序列 然后计对应的分数
     * <p>
     * 超时
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public long maxScore(int[] nums1, int[] nums2, int k) {
        if (nums1 == null || nums2 == null || k <= 0) return 0;
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> all = new ArrayList<>();
        process(nums1, 0, k, list, all);
        long ans = Integer.MIN_VALUE;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (List<Integer> l : all) {
            // 求和
            long sum = 0;
            for (Integer i : l) {
                pq.add(nums2[i]);
                sum += nums1[i];
            }
            ans = Math.max(ans, sum * pq.poll());
            pq.clear();
        }
        return ans;
    }

    public void process(int[] nums1, int index, int k, List<Integer> cur, List<List<Integer>> all) {
        if (index == nums1.length) {
            if (k == 0) {
                List<Integer> newList = new ArrayList<>(cur);
                all.add(newList);
            }
        } else {
            // 选择当前元素不选择当前元素
            process(nums1, index + 1, k, cur, all);
            if (k > 0) {
                cur.add(index);
                process(nums1, index + 1, k - 1, cur, all);
                cur.remove(cur.size() - 1); // 回溯
            }
        }
    }

    /**
     *
     * 贪心排序 + 堆优化
     *
     *
     *
     * 维护一个大小为k的窗口（在小跟堆体现）
     *
     *
     * 把 nums1和 nums2组合起来，按照 nums2[i] 从大到小排序。枚举 nums2[i] 作为序列的最小值，那么 nums1就只能在下标 ≤i 的数中选了。要选最大的 k 个数。
     *
     *
     *
     * <p>
     * 门槛堆的概念
     * 根据  703. 数据流中的第 K 大元素，这可以用一个大小固定为 k 的最小堆来做，如果当前元素大于堆顶，就替换堆顶，这样可以让堆中元素之和变大。
     *
     *
     * 假设你要组建一个篮球队，需要K个人
     * 规则：球队战斗力 = （总身高之和） * （全队最矮那个人的速度值）
     *
     * 1.把所有球员按照速度从快到慢排成一队
     * 2.先看钱K个速度最快的人 算一下战斗力
     * 3.再看K+1个球员 他的速度肯定比场上的人要慢
     * 4.他如果要进队 由于速度太慢 他会把全队的速度乘数拉低
     * 5.为了弥补速度的损失 必须在身高上做出巨大贡献 他必须要比在场上的身高最矮的那个人还要高 才有机会提升战斗力
     * 6.不断往后看 每个人都有机会作为最慢的那个人带队 只要能挤掉前面身高最矮的人 我们就计算一次新的战斗力最后取最高值
     *
     *
     * <p>
     * <p>
     * <p>
     * <p>
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public long maxScore2(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        Integer[] ids = new Integer[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
        }
        // 将数组2的下标进行排序 能够拿到数组2的从大到小的下标
        Arrays.sort(ids, (i, j) -> nums2[j] - nums2[i]);

//        Arrays.sort(ids, new Comparator<>() {.
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return Integer.compare(nums2[o2], nums1[o1]);
//            }
//        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long sum = 0;
        // 生成长度为K的堆
        // 枚举nums2[i]作为最小值怎么体现
        // 就是在遍历的过程中使用ids作为下标数组，
        for (int i = 0; i < k; i++) {
            sum += nums1[ids[i]];
            pq.offer(nums1[ids[i]]);
        }

        // 维护一个大小为k的小根堆 如果当前元素大于堆顶，就替换堆顶，这样可以让堆中元素之和变大。
        long ans = sum * nums2[ids[k - 1]];
        // 枚举nums2[i]作为最小值
        for (int i = k; i < n; i++) {
            int x = nums1[ids[i]];
            if (x > pq.peek()) {
                sum += x - pq.poll();
                pq.offer(x);
                ans = Math.max(ans, sum * nums2[ids[i]]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Code2542 code2542 = new Code2542();
        int[] nums1 = {1, 3, 3, 2};
        int[] nums2 = {2, 1, 3, 4};
        int k = 3;
        long result = code2542.maxScore(nums1, nums2, k);
        System.out.println("最大可能的分数: " + result); // 输出最大可能的分数

        long result2 = code2542.maxScore2(nums1, nums2, k);

        System.out.println("最大可能的分数2: " + result2); // 输出最大可能的分数
    }
}
