import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 *
 */
public class Code056 {

    public int[][] merge(int[][] intervals) {
        List<int[]> list = new ArrayList<>();
        // 根据开始位置进行排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        list.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] arr = list.get(list.size() - 1);
            if (intervals[i][0] <= arr[1]) {
                // 合并
                list.get(list.size() - 1)[1] = Math.max(intervals[i][1], arr[1]);
            }else {
                list.add(intervals[i]);
            }
        }
        // 转换答案
        return list.toArray(new int[list.size()][2]);
    }
}
