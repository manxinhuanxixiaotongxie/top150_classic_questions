package leetcode75;

public class Code1143 {
    public int longestCommonSubsequence(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        return process(s1, s2, s1.length - 1, s2.length - 1);
    }

    /**
     * 定义一个递归函数
     * 函数的含义是s1从0-i s2从0-j范围的最长公共子序列的长度
     *
     * @param s1
     * @param s2
     * @param i
     * @param j
     * @return
     */
    public int process(char[] s1, char[] s2, int i, int j) {
        if (i == 0 && j == 0) {
            return s1[i] == s2[j] ? 1 : 0;
        } else if (i == 0) {
            // i == 0 j != 0
            return s1[i] == s2[j] ? 1 : process(s1, s2, i, j - 1);
        } else if (j == 0) {
            // j ==0 i != 0
            return s1[i] == s2[j] ? 1 : process(s1, s2, i - 1, j);
        } else {
            // 两者都不为o
            // 第一种情况
            // s1[i] == s2[j]
            int ans = Integer.MIN_VALUE;
            if (s1[i] == s2[j]) {
                ans = Math.max(ans, 1 + process(s1, s2, i - 1, j - 1));
            } else {
                ans = Math.max(ans, process(s1, s2, i - 1, j));
                ans = Math.max(ans, process(s1, s2, i - 1, j - 1));
                ans = Math.max(ans, process(s1, s2, i, j - 1));
            }
            return ans;
        }
    }


    /**
     * 改成动态规划
     *
     * @param str1
     * @param str2
     * @return
     */
    public int longestCommonSubsequence2(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int[][] dp = new int[s1.length][s2.length];
        dp[0][0] = s1[0] == s2[0] ? 1 : 0;
        // 第一行
        int row = s1.length;
        int col = s2.length;
        for (int i = 1; i < col; i++) {
            dp[0][i] = s1[0] == s2[i] ? 1 : dp[0][i - 1];
        }
        // 第一列
        for (int i = 1; i < row; i++) {
            dp[i][0] = s1[i] == s2[0] ? 1 : dp[i - 1][0];
        }
        // 普遍位置
        // 从上到下 从左到右
        // 普遍位置依赖左边 上边 左上角的位置
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = s1[i] == s2[j] ? dp[i - 1][j - 1] + 1 : Math.max(dp[i - 1][j], Math.max(dp[i][j - 1], dp[i - 1][j - 1]));
            }
        }

        return dp[row - 1][col - 1];
    }

    /**
     * 空间压缩
     *
     * @param str1
     * @param str2
     * @return
     */
    public int longestCommonSubsequence3(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int[] dp = new int[s2.length];
        dp[0] = s1[0] == s2[0] ? 1 : 0;
        // 普遍位置依赖左边 上边 左上角的位置
        // 滚动更新数组
        for (int i = 1; i < s2.length; i++) {
            dp[i] = s1[0] == s2[i] ? 1 : dp[i - 1];
        }
        // 定义一个变量标记左上角
        int leftTop = Integer.MIN_VALUE;
        for (int i = 1; i < s1.length; i++) {
            // 总共要遍历这么多次
            leftTop = dp[0];
            dp[0] = s1[i] == s2[0] ? 1 : dp[0];
            for (int j = 1; j < dp.length; j++) {
                if (s1[i] == s2[j]) {
                    int temp = dp[j];
                    dp[j] = 1 + leftTop;
                    leftTop = temp;
                } else {
                    int temp = dp[j];
                    dp[j] = Math.max(dp[j - 1], Math.max(dp[j], leftTop));
                    leftTop = temp;
                }
            }
        }

        return dp[s2.length - 1];
    }
}
