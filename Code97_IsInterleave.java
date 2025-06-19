import java.util.Arrays;

/**
 * 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
 *
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
 *
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 注意：a + b 意味着字符串 a 和 b 连接。
 *
 */
public class Code97_IsInterleave {
    /**
     * 动态规划题目
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length();
        int m = s2.length();
        if (n + m != s3.length()) {
            return false;
        }

        int[][] memo = new int[n + 1][m + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // -1 表示没有计算过
        }
        return dfs(n - 1, m - 1, s1.toCharArray(), s2.toCharArray(), s3.toCharArray(), memo);
    }

    /**
     * s1 0-i范围
     * s2 0-j范围
     * 是否可以交错组成 s3 0-(i+j)范围
     *
     * @param i
     * @param j
     * @param s1
     * @param s2
     * @param s3
     * @param memo
     * @return
     */
    private boolean dfs(int i, int j, char[] s1, char[] s2, char[] s3, int[][] memo) {
        if (i < 0 && j < 0) {
            return true;
        }
        if (memo[i + 1][j + 1] != -1) { // 之前计算过
            return memo[i + 1][j + 1] == 1;
        }
        boolean res = i >= 0 && s1[i] == s3[i + j + 1] && dfs(i - 1, j, s1, s2, s3, memo) ||
                j >= 0 && s2[j] == s3[i + j + 1] && dfs(i, j - 1, s1, s2, s3, memo);
        memo[i + 1][j + 1] = res ? 1 : 0; // 记忆化
        return res;
    }
}
