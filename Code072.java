/**
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 */
public class Code072 {
    public int minDistance(String text1, String text2) {
        if (text1 == null && text2 == null) {
            return 0;
        } else if (text1 == null || text1.length() == 0) {
            return text2.length();
        } else if (text2 == null || text2.length() == 0) {
            return text1.length();
        }
        return process(text1.toCharArray(), text2.toCharArray(), text1.length() - 1, text2.length() - 1);
    }

    /**
     * 编辑距离
     *
     * @return
     */
    public int process(char[] str1, char[] str2, int l1, int l2) {
        if (l1 == 0 && l2 == 0) {
            return str1[l1] == str2[l2] ? 0 : 1;
        } else if (l1 == 0) {
            // l2不为0
            return str1[l1] == str2[l2] ? l2 : 1 + process(str1, str2, l1, l2 - 1);
        } else if (l2 == 0) {
            return str1[l1] == str2[l2] ? l1 : 1 + process(str1, str2, l1 - 1, l2);
        } else {
            // 都不为0
            // 计算最小值
            if (str1[l1] == str2[l2]) {
                // 相等
                return process(str1, str2, l1 - 1, l2 - 1);
            } else {
                int p1 = process(str1, str2, l1 - 1, l2) + 1;
                int p2 = process(str1, str2, l1, l2 - 1) + 1;
                int p3 = process(str1, str2, l1 - 1, l2 - 1) + 1;
                return Math.min(p1, Math.min(p2, p3));
            }
        }
    }

    /**
     * 改动态规划
     *
     * @param text1
     * @param text2
     * @return
     */
    public int minDistance2(String text1, String text2) {
        if (text1 == null && text2 == null) {
            return 0;
        } else if (text1 == null || text1.length() == 0) {
            return text2.length();
        } else if (text2 == null || text2.length() == 0) {
            return text1.length();
        }
        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();
        int len1 = str1.length;
        int len2 = str2.length;
        int[][] dp = new int[len1][len2];
        dp[0][0] = str1[0] == str2[0] ? 0 : 1;
        // 第0行
        for (int col = 1; col < len2; col++) {
            dp[0][col] = str1[0] == str2[col] ? col : dp[0][col - 1] + 1;
        }
        // 第一列
        for (int row = 1; row < len1; row++) {
            dp[row][0] = str1[row] == str2[0] ? row : dp[row - 1][0] + 1;
        }
        // 普遍位置
        for (int l1 = 1; l1 < len1; l1++) {
            for (int l2 = 1; l2 < len2; l2++) {
                if (str1[l1] == str2[l2]) {
                    dp[l1][l2] = dp[l1 - 1][l2 - 1];
                } else {
                    dp[l1][l2] = dp[l1 - 1][l2] + 1;
                    dp[l1][l2] = Math.min(dp[l1][l2], dp[l1][l2 - 1] + 1);
                    dp[l1][l2] = Math.min(dp[l1][l2], dp[l1 - 1][l2 - 1] + 1);
                }
            }
        }
        return dp[len1 - 1][len2 - 1];
    }
}
