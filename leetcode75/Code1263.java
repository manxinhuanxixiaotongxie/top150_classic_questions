package leetcode75;

import java.util.HashSet;

/**
 * 给你字符串 s 和整数 k 。
 * <p>
 * 请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。
 * <p>
 * 英文中的 元音字母 为（a, e, i, o, u）。
 */
public class Code1263 {

    private HashSet<Character> set = new HashSet<>();
    boolean init = false;

    private void init() {
        if (init) return;
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
    }

    public int maxVowels(String s, int k) {
        init();
        if (s == null || s.length() == 0) {
            return 0;
        }
        int ans = 0;
        int count = 0;
        char[] str = s.toCharArray();
        int N = str.length;
        for (int i = 0; i < N; i++) {
            if (set.contains(str[i])) {
                count++;
            }
            if (i < k - 1) {
                continue;
            }
            ans = Math.max(ans, count);
            if (set.contains(str[i - k + 1])) {
                count--;
            }
        }

        return ans;
    }


}
