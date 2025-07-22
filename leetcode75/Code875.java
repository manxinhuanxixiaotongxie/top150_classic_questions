package leetcode75;

/**
 * 珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 h 小时后回来。
 * <p>
 * 珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
 * <p>
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 * <p>
 * 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。
 */
public class Code875 {
    public int minEatingSpeed(int[] piles, int h) {
        // 在h小时吃完所有香蕉的最小速度
        // 二分查找
        int N = piles.length;
        // h必须要大于等于N
        // 每堆香蕉至少要吃一个小时 不足一个小时 按照一个小时安排
        // 如果 h = 20  N = 10 意味着 每堆平均可以分两个小时
        // 实际是不需要这么长时间
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, piles[i]);
        }
        // 找到二分的顶点
        // 从0-max+1进行二分查找
        // 如果哪个位置能够满足吃完的条件 那么这就是可以吃完的速度
        int left = 0;
        int right = max;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (check2(piles, mid, h)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }


    public boolean check(int[] piles, int k, int h) {
        // 检查在速度k下能不能在h小时吃完香蕉
        for (int i = 0; i < piles.length; i++) {
            // 每堆需要的时间
            // 一定要-1向下取整
            h -= (((piles[i] - 1) / k) + 1);
        }
        return h >= 0;
    }

    private boolean check2(int[] piles, int mid, int h) {
        int sum = piles.length;
        for (int pile : piles) {
            sum += (pile - 1) / mid;
            if (sum > h) {
                return false;
            }
        }
        return true;
    }
}
