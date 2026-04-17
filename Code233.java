/**
 * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
 *
 */
public class Code233 {
    // 数位dp的暴力递归
    public int countDigitOne(int n) {
        return process(0, 0, true, true, String.valueOf(0).toCharArray(),
                String.valueOf(n).toCharArray());
    }

    // 数位dp
    // i 来到了第多少位
    // limitLow 低位限制 如果低位出现了限制 那么后续的i位最小值就只能填到低位 不能从0开始
    // limitHigh 高位限制 如果出现了高位限制 那么后续的i为的最大值就只能到高位 不能到9
    public int process(int i, int cnt, boolean limitLow, boolean limitHigh, char[] low, char[] high) {
        if (i == high.length) {
            return cnt;
        }
        // 计算高低位的差值
        int diff = high.length - low.length;
        // 低位的限制 不能这样写 i > diff的时候如果
        // 低位不受限制 低位可以从0开始 如果 低位受到限制 低位只能从low[i]开始
//        int lo = i < diff?0:low[i];
        int lo = i > diff && limitLow ? low[i] - '0' : 0;
        // 高位限制
        int hi = limitHigh ? high[i] - '0' : 9;

        int ans = 0;
        for (int j = lo; j <= hi; j++) {
            ans += process(i + 1, cnt + ((j == 1) ? 1 : 0),
                    limitLow && j == lo, limitHigh && j == hi, low, high);
        }
        return ans;
    }

    /**
     * 改成记忆化搜索
     *
     * @param n
     * @return
     */
    public int countDigitOne2(int n) {
        char[] charArray = String.valueOf(n).toCharArray();
        int[][] dp = new int[charArray.length + 1][10];
        return process2(0, 0, true, true, String.valueOf(0).toCharArray(),
                charArray, dp);
    }

    public int process2(int i, int cnt, boolean limitLow, boolean limitHigh, char[] low, char[] high,
                        int[][] dp) {
        if (i == high.length) {
            return cnt;
        }
        if (!limitLow && !limitHigh && dp[i][cnt] != 0) {
            return dp[i][cnt];
        }
        // 计算高低位的差值
        int diff = high.length - low.length;
        // 低位的限制 不能这样写 i > diff的时候如果
        // 低位不受限制 低位可以从0开始 如果 低位受到限制 低位只能从low[i]开始
        int lo = i > diff && limitLow ? low[i] - '0' : 0;
        // 高位限制
        int hi = limitHigh ? high[i] - '0' : 9;

        int ans = 0;
        for (int j = lo; j <= hi; j++) {
            ans += process2(i + 1, cnt + ((j == 1) ? 1 : 0),
                    limitLow && j == lo, limitHigh && j == hi, low, high, dp);
        }
        if (!limitLow && !limitHigh) {
            dp[i][cnt] = ans;
        }
        return ans;
    }

}
