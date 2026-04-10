import java.util.LinkedList;

/**
 * 一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 *
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 *
 * 给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
 *
 */
public class Code134 {
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        // 两倍长度
        int n = gas.length;
        // 前缀和
        int[] sub = new int[n << 1];
        for (int i = 0; i < n; i++) {
            sub[i] = gas[i] - cost[i];
            sub[i + n] = gas[i] - cost[i];
        }
        // 处理成差值数组
        // 处理成前缀和
        int[] sum = new int[n << 1];
        sum[0] = sub[0];
        // 已经是前缀和了
        for (int i = 1; i < n << 1; i++) {
            sum[i] = sum[i - 1] + sub[i];
        }
        // 滑动窗口最小值 要保证最小值是非负的 那么说明这个位置出发是可以走完一圈的 窗口的大小就是数组的长度
        LinkedList<Integer> window = new LinkedList<>();
        for (int i = 0; i < n - 1; i++) {
            while (!window.isEmpty() && sum[window.peekLast()] >= sum[i]) {
                window.pollLast();
            }
            window.offerLast(i);
        }
        for (int i = 0; i < n; i++) {
            int index = i + n - 1;
            while (!window.isEmpty() && sum[window.peekLast()] >= sum[index]) {
                window.pollLast();
            }
            window.offerLast(index);
            // 判断最小值
            int minIndex = window.peekFirst();
            int pre = i == 0 ? 0 : sum[i - 1];
            if (sum[minIndex] - pre >= 0) {
                return i;
            }
            // 移除i位置
            if (minIndex == i) {
                window.pollFirst();
            }
        }

        return -1;
    }
}
