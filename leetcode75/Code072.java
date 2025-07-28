package leetcode75;

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
     * 改dp  会少算 待分析
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance2(String word1, String word2) {
        char[] str1 = word1.toCharArray();
        char[] str2 = word2.toCharArray();
        if (str1.length == 0) return str2.length;
        if (str2.length == 0) return str1.length;
        int[][] dp = new int[str1.length][str2.length];
        dp[0][0] = str1[0] == str2[0] ? 0 : 1;
        // 先填写第一行
        for (int j = 1; j < str2.length; j++) {
            // index1==0 index2 != 0
            // dp[0][j]
            boolean found = false;
            for (int index2 = 0; index2 <= j; index2++) {
                if (str1[0] == str2[index2]) {
                    found = true;
                    break;
                }
            }
            if (found) {
                dp[0][j] = j;
            } else {
                dp[0][j] = j + 1;
            }
        }
        // 先填写第一列
        for (int i = 1; i < str1.length; i++) {
            // dp[i][0]
            boolean found = false;
            for (int index1 = 0; index1 <= i; index1++) {
                if (str1[index1] == str2[0]) {
                    found = true;
                    break;
                }
            }
            if (found) {
                dp[i][0] = i;
            } else {
                dp[i][0] = i + 1;
            }
        }
        // 普遍位置
        for (int index1 = 1; index1 < str1.length; index1++) {
            for (int index2 = 1; index2 < str2.length; index2++) {
                dp[index1][index2] = Integer.MAX_VALUE;
                if (str1[index1] == str2[index2]) {
                    dp[index1][index2] = dp[index1 - 1][index2 - 1];
                } else {
                    // 第一种情况 替换
                    dp[index1][index2] = Math.min(dp[index1][index2], dp[index1 - 1][index2 - 1] + 1);
                    // 第2种情况 插入str1
                    dp[index1][index2] = Math.min(dp[index1][index2], dp[index1][index2 - 1] + 1);
                    // 第3种情况 删除str1
                    dp[index1][index2] = Math.min(dp[index1][index2], dp[index1 - 1][index2] + 1);
                }
            }
        }
        return dp[str1.length - 1][str2.length - 1];
    }

    public static void main(String[] args) {
        Code072 code = new Code072();
        System.out.println(code.minDistance("pneuoscopicsilicovolcanoconiosis", "ultramicroscopically"));
        System.out.println(code.minDistance2("pneuoscopicsilicovolcanoconiosis", "ultramicroscopically"));
    }
}
