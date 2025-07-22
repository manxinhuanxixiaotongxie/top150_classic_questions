package leetcode75;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给你一个下标从 0 开始的整数数组 costs ，其中 costs[i] 是雇佣第 i 位工人的代价。
 * <p>
 * 同时给你两个整数 k 和 candidates 。我们想根据以下规则恰好雇佣 k 位工人：
 * <p>
 * 总共进行 k 轮雇佣，且每一轮恰好雇佣一位工人。
 * 在每一轮雇佣中，从最前面 candidates 和最后面 candidates 人中选出代价最小的一位工人，如果有多位代价相同且最小的工人，选择下标更小的一位工人。
 * <p>
 * 比方说，costs = [3,2,7,7,1,2] 且 candidates = 2 ，第一轮雇佣中，我们选择第 4 位工人，因为他的代价最小 [3,2,7,7,1,2] 。
 * <p>
 * 第二轮雇佣，我们选择第 1 位工人，因为他们的代价与第 4 位工人一样都是最小代价，而且下标更小，[3,2,7,7,2] 。注意每一轮雇佣后，剩余工人的下标可能会发生变化。
 * 如果剩余员工数目不足 candidates 人，那么下一轮雇佣他们中代价最小的一人，如果有多位代价相同且最小的工人，选择下标更小的一位工人。
 * 一位工人只能被选择一次。
 * 返回雇佣恰好 k 位工人的总代价。
 */

public class Code2462 {

    /**
     * 超时
     *
     * @param costs
     * @param k
     * @param candidates
     * @return
     */
    public long totalCost(int[] costs, int k, int candidates) {
        // 小标从0开始
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 小跟堆
                // 值相等 下标小的在前面
                // 值不相等 值小的在前面
                return o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
            }
        });
        long ans = 0;
        int N = costs.length;
        int[] help = costs;
        while (k > 0) {
            int begin = 0;
            for (; begin < Math.min(candidates, N); begin++) {
                pq.offer(new int[]{help[begin], begin});
            }
            // 此时begin来到了candidates位置
            int next = N - candidates ;
            next = Math.max(begin, next);
            for (int i = next; i < N; i++) {
                pq.offer(new int[]{help[i], i});
            }
            if (!pq.isEmpty()) {
                // 选择最小的值
                int[] poll = pq.poll();
                help = getAfterChoose(help, poll[1]);
                ans += poll[0];
                N = help.length;
                pq.clear();
            }
            k--;
        }

        return ans;
    }

    public int[] getAfterChoose(int[] costs, int index) {
        int[] res = new int[costs.length - 1];
        int j = 0;
        for (int i = 0; i < costs.length; i++) {
            if (i != index) {
                res[j++] = costs[i];
            }
        }
        return res;
    }

    /**
     * 优化 灵神的思路
     * 使用两个小根堆维护左右两边
     *
     * @param costs
     * @param k
     * @param candidates
     * @return
     */
    public long totalCost2(int[] costs, int k, int candidates) {
        int n = costs.length;
        long ans = 0;
        // 判断长度 如果candidates * 2 + k > n 意味着选K个数 左右两边一定会重合
        // 那么就直接将数组排序，取前k个数即可
        if (candidates * 2 + k > n) {
            Arrays.sort(costs);
            for (int i = 0; i < k; i++) {
                ans += costs[i];
            }
            return ans;
        }

        PriorityQueue<Integer> pre = new PriorityQueue<>();
        PriorityQueue<Integer> suf = new PriorityQueue<>();
        for (int i = 0; i < candidates; i++) {
            pre.offer(costs[i]);
            suf.offer(costs[n - 1 - i]);
        }

        int i = candidates;
        int j = n - 1 - candidates;
        while (k-- > 0) {
            // 左右两边 同时两个堆进行维护
            if (pre.peek() <= suf.peek()) {
                ans += pre.poll();
                pre.offer(costs[i++]);
            } else {
                ans += suf.poll();
                suf.offer(costs[j--]);
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        Code2462 code2462 = new Code2462();
//        int[] costs = {17,12,10,2,7,2,11,20,8};
        int[] costs = {1, 2, 4, 1};
//        int[] costs = {50,80,34,9,86,20,67,94,65,82,40,79,74,92,84,37,19,16,85,20,79,25,89,55,67,84,3,79,38,16,44,2,54,58};
        int k = 3;
        int candidates = 3;
        long ans = code2462.totalCost(costs, k, candidates);
        long ans2 = code2462.totalCost2(costs, k, candidates);
        System.out.println(ans); // 输出：11
        System.out.println(ans2); // 输出：11
    }
}
