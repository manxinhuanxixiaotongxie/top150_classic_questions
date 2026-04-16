import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 * 219的附加题目
 * 给你一个整数数组 nums 和两个整数 indexDiff 和 valueDiff 。
 * <p>
 * 找出满足下述条件的下标对 (i, j)：
 * <p>
 * i != j,
 * abs(i - j) <= indexDiff
 * abs(nums[i] - nums[j]) <= valueDiff
 * 如果存在，返回 true ；否则，返回 false 。
 *
 */
public class Code220 {
    /**
     * 单调窗口有问题 无法解决这个题目 因为题目中间会有非常接近的值 但是不满足条件的值 可能会被单调窗口过滤掉
     *
     * @param nums
     * @param indexDiff
     * @param valueDiff
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        // 转换一下    求的是  i-indexOff = j 到i位置 是否 存在abs(nums[i] - nums[j]) <=valueDiff
        // 窗口大小固定 窗口的最大值 窗口的最小值 都知道了 那么就是判断
        // 最大值窗口
        LinkedList<Integer> maxWindow = new LinkedList<>();
        // 最小值窗口
        LinkedList<Integer> minWindow = new LinkedList<>();
        // 这两个存放的是下标 窗口的大小是indexDiff
        for (int i = 0; i < nums.length; i++) {
            if (i >= indexDiff) {
                // 结算
                if (Math.abs(nums[i] - nums[maxWindow.peekFirst()]) <= valueDiff ||
                        Math.abs(nums[minWindow.peekFirst()] - nums[i]) <= valueDiff) {
                    return true;
                }
            }

            // 最大值窗口更新
            while (!maxWindow.isEmpty() && nums[maxWindow.peekLast()] <= nums[i]) {
                maxWindow.pollLast();
            }
            maxWindow.offerLast(i);
            while (!minWindow.isEmpty() && nums[minWindow.peekLast()] >= nums[i]) {
                minWindow.pollLast();
            }
            minWindow.offerLast(i);
            if (i - minWindow.peekFirst() >= indexDiff) {
                minWindow.pollFirst();
            }
            if (i - maxWindow.peekFirst() >= indexDiff) {
                maxWindow.pollFirst();
            }
        }
        // 单独结算最后一个窗口
        if (!maxWindow.isEmpty() && !minWindow.isEmpty() &&
                (Math.abs(maxWindow.peekFirst() - minWindow.peekFirst()) <= indexDiff &&
                        Math.abs(nums[minWindow.peekFirst()] - nums[maxWindow.peekFirst()]) <= valueDiff)) {
            return true;
        }

        return false;
    }

    // 方法2：使用 TreeMap 维护有序窗口，正确处理所有情况
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int indexDiff, int valueDiff) {
        TreeMap<Long, Integer> window = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            long val = (long) nums[i];
            // 找窗口中 ≤ val 的最大值
            Map.Entry<Long, Integer> floor = window.floorEntry(val);
            if (floor != null && val - floor.getKey() <= valueDiff) return true;
            // 找窗口中 ≥ val 的最小值
            Map.Entry<Long, Integer> ceiling = window.ceilingEntry(val);
            if (ceiling != null && ceiling.getKey() - val <= valueDiff) return true;
            window.put(val, i);
            // 移除超出窗口的元素
            if (i >= indexDiff) {
                window.remove((long) nums[i - indexDiff]);
            }
        }
        return false;
    }

    static void main() {
        Code220 code220 = new Code220();
        int[] nums = {1, 2, 2, 3, 4, 5};
        int indexDiff = 3;
        int valueDiff = 0;
        boolean res = code220.containsNearbyAlmostDuplicate(nums, indexDiff, valueDiff);
        System.out.println(res);
    }
}
