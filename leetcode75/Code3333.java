package leetcode75;

import java.util.HashMap;

public class Code3333 {
    /**
     * 暴力解法
     * <p>
     * 题目给定word的长度5*10^5
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
        HashMap<String, Integer> map = new HashMap<>();
        return process(str, 0, k, 0, map);
    }

    public int process(char[] str, int index, int k, int count, HashMap<String, Integer> map) {
        if (map.containsKey(index + "-" + count)) {
            return map.get(index + "-" + count);
        }
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
                int next = process(str, i, k, count + j, map);
                if (next != Integer.MAX_VALUE) {
                    ans = (ans + next % 1000000007) % 1000000007;
                }
            }
            map.put(index + "-" + count, ans % 1000000007);
            return ans;
        }
    }
}
