package leetcode75;

public class Code3333 {
    /**
     * 暴力解法
     *
     * @param word
     * @param k
     * @return
     */
    public int possibleStringCount(String word, int k) {
        if (word == null || word.length() == 0 || k == 0) {
            return 0;
        }
        if (k >= word.length()) return 1;
        char[] str = word.toCharArray();
        return process(str, 0, k, 0);
    }

    public int process(char[] str, int index, int k, int count) {
        if (index == str.length) {
            return count < k ? Integer.MAX_VALUE : 1;
        } else {
            // 总共有多少种拆分方案
            int ans = 0;
            // 拆分方案
            // 想等的字符有多少种
            int i = index;
            while (i < str.length && str[i] == str[index]) {
                i++;
            }
            // 总共有index到i-1的字符是相同的
            for (int j = 1; j <= (i - index); j++) {
                // 当前字符选多少个
                // 下一个位置是i
                int next = process(str, i, k, count + j);
                if (next != Integer.MAX_VALUE) {
                    ans += next;
                }
            }
            return ans;
        }
    }
}
