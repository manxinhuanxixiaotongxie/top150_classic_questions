package leetcode75;

import java.util.ArrayList;
import java.util.Arrays;
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
     * 把 nums1和 nums2组合起来，按照 nums2[i] 从大到小排序。枚举 nums2[i] 作为序列的最小值，那么 nums1
     * 就只能在下标 ≤i 的数中选了。要选最大的 k 个数。
     * <p>
     * <p>
     * 由于枚举的nums2[i]是最小值，则剩下的k-1个数一定是>=nums2[i]的，又因为nums2已经被从大到小排好序，所以这剩下的k-1个数下标一定<=i，即nums1就只能在下标 ≤i 的数中选了。
     * <p>
     * 根据  703. 数据流中的第 K 大元素，这可以用一个大小固定为 k 的最小堆来做，如果当前元素大于堆顶，就替换堆顶，这样可以让堆中元素之和变大。
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * ？？？？？？？？？？？？？？
     * <p>
     * 作者：灵茶山艾府
     * 链接：https://leetcode.cn/problems/maximum-subsequence-score/solutions/2073033/zhuan-huan-wen-ti-zui-xiao-dui-by-endles-9t8t/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
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
        // 对下标排序，不影响原数组的顺序
        Arrays.sort(ids, (i, j) -> nums2[j] - nums2[i]);

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
