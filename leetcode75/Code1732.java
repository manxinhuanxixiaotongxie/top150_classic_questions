package leetcode75;

/**
 * 有一个自行车手打算进行一场公路骑行，这条路线总共由 n + 1 个不同海拔的点组成。自行车手从海拔为 0 的点 0 开始骑行。
 * <p>
 * 给你一个长度为 n 的整数数组 gain ，其中 gain[i] 是点 i 和点 i + 1 的 净海拔高度差（0 <= i < n）。请你返回 最高点的海拔 。
 */
public class Code1732 {
    public int largestAltitude(int[] gain) {
        int[] help = new int[gain.length + 1];
        for (int i = 0; i < gain.length; i++) {
            help[i + 1] = help[i] + gain[i];
        }
        int answer = help[0];
        for (int i = 1; i < help.length; i++) {
            if (help[i] > answer) {
                answer = help[i];
            }
        }
        return answer;
    }

    /**
     * 空间复杂度O（1）的做法
     *
     * @param gain
     * @return
     */
    public int largestAltitude2(int[] gain) {
        int ans = 0;
        int pre = 0;
        for (int i = 0; i < gain.length; i++) {
            // 差值
            pre = pre + gain[i];
            ans = Math.max(ans, pre);
        }
        return ans;
    }
}
