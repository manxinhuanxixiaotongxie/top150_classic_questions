package leetcode75;

import java.util.Arrays;

/**
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 */
public class Code072 {
    public int minDistance(String word1, String word2) {
        char[] str1 = word1.toCharArray();
        char[] str2 = word2.toCharArray();
        if (str1.length == 0) return str2.length;
        if (str2.length == 0) return str1.length;
        // a ab
        return process(str1, str2, str1.length - 1, str2.length - 1);
    }

    public int process(char[] str1, char[] str2, int index1, int index2) {
        if (index1 == 0 && index2 == 0) {
            return str1[index1] == str2[index2] ? 0 : 1;
        } else if (index1 == 0) {
            // index2 !=0
            // 这里应该要计算是不是包含
            for (int i = index2; i >= 0; i--) {
                if (str1[index1] == str2[i]) {
                    return index2;
                }
            }
            return index2 + 1;
        } else if (index2 == 0) {
            // index1 !=0
            for (int i = index1; i >= 0; i--) {
                if (str1[i] == str2[index2]) {
                    return index1;
                }
            }
            return index1 + 1;
        } else {
            // 两者都不为0
            int ans = Integer.MAX_VALUE;
            if (str1[index1] == str2[index2]) {
                // 不需要进行处理
                ans = Math.min(ans, process(str1, str2, index1 - 1, index2 - 1));
            } else {
                // 不相等
                // 第一种情况 替换当前字符
                ans = Math.min(ans, process(str1, str2, index1 - 1, index2 - 1) + 1);
                // 新增一个字符
                ans = Math.min(ans, process(str1, str2, index1, index2 - 1) + 1);
                // 删除字符
                ans = Math.min(ans, process(str1, str2, index1 - 1, index2) + 1);
            }
            return ans;
        }
    }

    /**
     *
     * 改动态规划
     *
     * @return
     */
    public int minDistance2(String text1, String text2) {
        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();
        if (str1.length == 0) return str2.length;
        if (str2.length == 0) return str1.length;
        // 改成动态规划
        int[][] dp = new int[str1.length][str2.length];
        for (int i = 0; i < str1.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        // 左上角位置
        dp[0][0] = str1[0] == str2[0] ? 0 : 1;
        // 第一行
        for (int c = 1; c < str2.length; c++) {
            dp[0][c] = str1[0] == str2[c] ? c : dp[0][c - 1] + 1;
        }
        // 第一列
        for (int r = 1; r < str1.length; r++) {
            dp[r][0] = str1[r] == str2[0] ? r : dp[r - 1][0] + 1;
        }
        // 普遍位置
        for (int index1 = 1; index1 < str1.length; index1++) {
            for (int index2 = 1; index2 < str2.length; index2++) {
                if (str1[index1] == str2[index2]) {
                    dp[index1][index2] = Math.min(dp[index1][index2], dp[index1 - 1][index2 - 1]);
                } else {
                    dp[index1][index2] = Math.min(dp[index1][index2], dp[index1 - 1][index2 - 1] + 1);
                    dp[index1][index2] = Math.min(dp[index1][index2], dp[index1][index2 - 1] + 1);
                    dp[index1][index2] = Math.min(dp[index1][index2], dp[index1 - 1][index2] + 1);

                }
            }
        }
        return dp[str1.length - 1][str2.length - 1];
    }

    /**
     * 空间压缩计算
     * <p>
     * 普遍位置依赖分析：依赖左上角 左边 上边位置
     *
     * @param text1
     * @param text2
     * @return
     */
    public int minDistance3(String text1, String text2) {
        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();
        if (str1.length == 0) return str2.length;
        if (str2.length == 0) return str1.length;
        // 整合成一维数组
        int[] dp = new int[str2.length];
        // 先把一行位置填写了
        dp[0] = str1[0] == str2[0] ? 0 : 1;
        for (int c = 1; c < str2.length; c++) {
            if (str1[0] == str2[c]) {
                dp[c] = c;
            } else {
                dp[c] = dp[c - 1] + 1;
            }
        }
        // 总共要循环str1这么多次
        for (int index1 = 1; index1 < str1.length; index1++) {
            int leftTop = dp[0];
            dp[0] = str1[index1] == str2[0] ? index1 : dp[0] + 1;
            for (int index2 = 1; index2 < str2.length; index2++) {
                // 普遍位置
                int temp = dp[index2];
                if (str1[index1] == str2[index2]) {
                    dp[index2] = leftTop;
                } else {
                    dp[index2] = Math.min(leftTop + 1, Math.min(dp[index2] + 1, dp[index2 - 1] + 1));
                }
                leftTop = temp;
            }
        }

        return dp[str2.length - 1];
    }


    public static void main(String[] args) {
        Code072 code = new Code072();
        System.out.println(code.minDistance("pneuoscopicsilicovolcanoconiosis", "ultramicroscopically"));
        System.out.println(code.minDistance2("pneuoscopicsilicovolcanoconiosis", "ultramicroscopically"));
    }
}
