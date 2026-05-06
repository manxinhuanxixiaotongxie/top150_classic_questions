package leetcode75;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 有一些球形气球贴在一堵用 XY 平面表示的墙面上。墙面上的气球记录在整数数组 points ，其中points[i] = [xstart, xend]
 * 表示水平直径在 xstart 和 xend之间的气球。你不知道气球的确切 y 坐标。
 * <p>
 * 一支弓箭可以沿着 x 轴从不同点 完全垂直 地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend，
 * 且满足  xstart ≤ x ≤ xend，则该气球会被 引爆 。可以射出的弓箭的数量 没有限制 。 弓箭一旦被射出之后，可以无限地前进。
 * <p>
 * 给你一个数组 points ，返回引爆所有气球所必须射出的 最小 弓箭数 。
 */
public class Code452 {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, (point1, point2) -> Integer.compare(point1[1], point2[1]));
        int pos = points[0][1];
        int ans = 1;
        for (int[] balloon : points) {
            if (balloon[0] > pos) {
                pos = balloon[1];
                ++ans;
            }
        }
        return ans;
    }

    /**
     * 按照开始时间进行排序
     * @param points
     * @return
     */
    public int findMinArrowShots2(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        int ans = points.length;
        int max = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= max) {
                ans--;
                // 为什么要用min 而不是max
                // 问题在于：按开始时间排序时，重叠的气球需要找公共交集，箭必须射在交集内。所以 max 应该取 Math.min（交集右边界），而不是 Math.max。
                max = Math.min(max, points[i][1]);
            }else {
                // 不能射爆
                max = points[i][1];
            }
        }
        return ans;
    }
}
