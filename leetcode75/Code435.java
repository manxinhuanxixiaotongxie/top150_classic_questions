package leetcode75;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，
 * 使剩余区间互不重叠 。
 * <p>
 * 注意 只在一点上接触的区间是 不重叠的。例如 [1, 2] 和 [2, 3] 是不重叠的。
 */
public class Code435 {
    /**
     * 贪心 根据结束时间的早晚进行排序
     * 证明：
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        int ans = 0;
        // 排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return  o1[1] - o2[1];
            }
        });
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (interval[0] >= max) {
                max = Math.max(max, interval[1]);
            } else {
                ans++;
            }
        }


        return ans;
    }

    public static void main(String[] args) {
        Code435 code435 = new Code435();
        // [[-52,31],[-73,-26],[82,97],[-65,-11],[-62,-49],[95,99],[58,95],[-31,49],[66,98],[-63,2],[30,47],[-40,-26]]
        int[][] intervals = {
                {-52, 31}, {-73, -26}, {82, 97}, {-65, -11}, {-62, -49},
                {95, 99}, {58, 95}, {-31, 49}, {66, 98}, {-63, 2},
                {30, 47}, {-40, -26}
        };
        System.out.println(code435.eraseOverlapIntervals(intervals));
    }
}
