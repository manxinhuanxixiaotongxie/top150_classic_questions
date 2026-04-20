import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 给定两个以 非递减顺序排列 的整数数组 nums1 和 nums2 , 以及一个整数 k 。
 * <p>
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
 * <p>
 * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
 *
 */
public class Code373_KSmallestPairs {

    /**
     *
     *  将该问题转化成“合并M个有序链表”的变体。
     * 想象成一个矩阵  每一行都是升序的 每一列都是升序的
     *
     *
     * 借助小跟堆进行优化
     *
     * 每次 (i,j) 出堆时，把候选项 (i+1,j) 和 (i,j+1) 入堆。（和「初步思路」中的讨论一样，其它的不会比这两个更小。）
     *
     * 但这会导致一个问题：例如当 (1,0) 出堆时，会把 (1,1) 入堆；当 (0,1) 出堆时，也会把 (1,1) 入堆，这样堆中会有重复元素。
     * 为了避免有重复元素，还需要额外用一个哈希表记录在堆中的下标对。只有当下标对不在堆中时，才能入堆。
     *
     * 能否不用哈希表呢？
     *
     * 根据上面的讨论，出堆的下标对只能是 (i−1,j) 和 (i,j−1)。
     *
     * 只要保证 (i−1,j) 和 (i,j−1) 的其中一个会将 (i,j) 入堆，而另一个什么也不做，就不会出现重复了！
     *
     * 不妨规定 (i,j−1) 出堆时，将 (i,j) 入堆；而 (i−1,j) 出堆时只计入答案，其它什么也不做。
     *
     * 换句话说，在 (i,j) 出堆时，只需将 (i,j+1) 入堆，无需将 (i+1,j) 入堆。
     *
     * 但若按照该规则，初始仅把 (0,0) 入堆的话，只会得到 (0,1),(0,2),⋯ 这些下标对。
     *
     * 所以初始不仅要把 (0,0) 入堆，(1,0),(2,0),⋯ 这些都要入堆。
     *
     * 代码实现时，为了方便比较大小，实际入堆的是三元组 (a[i]+b[j],i,j)。
     *
     *
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, (o1, o2) -> {
            return nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]];
        });
        List<List<Integer>> ans = new ArrayList<>();
        int m = nums1.length;
        int n = nums2.length;
        for (int i = 0; i < Math.min(m, k); i++) {
            pq.offer(new int[]{i, 0});
        }
        while (k-- > 0 && !pq.isEmpty()) {
            int[] idxPair = pq.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[idxPair[0]]);
            list.add(nums2[idxPair[1]]);
            ans.add(list);
            if (idxPair[1] + 1 < n) {
                pq.offer(new int[]{idxPair[0], idxPair[1] + 1});
            }
        }

        return ans;
    }

    /**
     * 思路有问题，不能保证每次+1位置较小的一个就是下一个最小的数对
     *
     * 比如 数字（1,1） 可能由（0,1）加入 也可能由（1,0）加入
     *
     */
//    public List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
//        PriorityQueue<List<Integer>> pq = new PriorityQueue<>(new Comparator<>() {
//            @Override
//            public int compare(List<Integer> o1, List<Integer> o2) {
//                return nums1[o1.get(0)] + nums2[o1.get(1)] - nums1[o2.get(0)] - nums2[o2.get(1)];
//            }
//        });
//        pq.offer(Arrays.asList(0, 0));
//        List<List<Integer>> ans = new ArrayList<>();
//        while (k-- > 0 && !pq.isEmpty()) {
//            List<Integer> poll = pq.poll();
//            int num1 = poll.get(0);
//            int num2 = poll.get(1);
//            ans.add(Arrays.asList(nums1[num1], nums2[num2]));
//            // 找到+1位置较小的一个
//            int l1 = (num1 + 1) % nums1.length;
//            int l2 = (num2 + 1) % nums2.length;
//            if (nums1[num1] + nums2[l2] > nums1[l1] + nums2[num2]) {
//                pq.offer(Arrays.asList(l1, num2));
//            } else {
//                pq.offer(Arrays.asList(num1, l2));
//            }
//
//        }
//
//        return ans;
//    }
}
