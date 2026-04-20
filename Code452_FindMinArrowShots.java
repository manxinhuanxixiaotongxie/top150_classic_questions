import java.util.Arrays;

/**
 * 有一些球形气球贴在一堵用 XY 平面表示的墙面上。墙面上的气球记录在整数数组 points ，其中points[i] = [xstart, xend]
 * 表示水平直径在 xstart 和 xend之间的气球。你不知道气球的确切 y 坐标。
 * <p>
 * 一支弓箭可以沿着 x 轴从不同点 完全垂直 地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend，
 * 且满足  xstart ≤ x ≤ xend，则该气球会被 引爆 。可以射出的弓箭的数量 没有限制 。 弓箭一旦被射出之后，可以无限地前进。
 * <p>
 * 给你一个数组 points ，返回引爆所有气球所必须射出的 最小 弓箭数 。
 * <p>
 * 提示:
 * <p>
 * 1 <= points.length <= 10^5
 * points[i].length == 2
 * -2^31 <= xstart < xend <= 2^31 - 1
 */
public class Code452_FindMinArrowShots {

    /**
     * 从左到右思考，想一想，第一个点（最左边的点）放在哪最好？
     *
     * 例如 points=[[1,6],[6,7],[2,8],[7,10]]。第一个点放在 x=1 处，只能满足一个区间 [1,6]，而如果放在 x=2 处，则可以满足区间 [1,6],[2,8]。
     * 进一步地，如果放在更靠右的 x=6 处，则可以满足三个区间 [1,6],[6,7],[2,8]。注意不能放在 x=7 处，这样没法满足区间 [1,6] 了。
     *
     * 在 x=6 放点后，问题变成剩下 n−3 个区间，在数轴上最少要放置多少个点，使得每个区间都包含至少一个点。这是一个规模更小的子问题，可以用同样的方法解决。
     *
     * 把区间按照右端点从小到大排序，这样第一个点就放在第一个区间的右端点处。去掉包含第一个点的区间后，第二个点就放在剩余区间的第一个区间的右端点处。依此类推。
     *
     * 总结：想清楚第一个点怎么放，是破解这道题的关键。
     *
     * 算法
     * 1.把区间按照右端点从小到大排序。
     * 2.初始化答案 ans=0，上一个放点的位置 pre=−∞。
     * 3.遍历区间，如果 start≤pre，那么这个区间已经包含点，跳过。
     * 4.如果 start>pre，那么必须放一个点，把 ans 加一。根据上面的讨论，当前区间的右端点就是放点的位置，更新 pre=end。
     * 5.遍历结束后，返回 ans。
     *
     *
     *
     * @param points
     * @return
     */
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
     * 区间合并
     *
     * @param points
     * @return
     */
    public int findMinArrowShots2(int[][] points) {
        // 防止相减式的比较造成溢出
        Arrays.sort(points, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        int n = points.length; // 合并一次，n-1
        for (int i = 1; i < points.length; i++) {
            // 求交集
            if (points[i][0] <= points[i - 1][1]) {
                //区间重叠，合并为交集
                n--;
                points[i][0] = Math.max(points[i][0], points[i - 1][0]);
                points[i][1] = Math.min(points[i][1], points[i - 1][1]);
            }
        }
        return n;
    }


}
