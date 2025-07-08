package leetcode75;

import java.util.HashSet;

public class Code392 {
    boolean init = false;
    HashSet<String> set = new HashSet<>();

    private void init(String t) {
        if (init) return;
        char[] str = t.toCharArray();
        process(str, 0, "", set);
    }

    private void process(char[] str, int index, String path, HashSet<String> set) {
        if (index == str.length) {
            set.add(path);
            return;
        }
        // 不选当前字符
        process(str, index + 1, path, set);
        // 选当前字符
        process(str, index + 1, path + str[index], set);
    }

    public boolean isSubsequence(String s, String t) {
        init(t);
        return set.contains(s);
    }

    public boolean isSubsequence2(String s, String t) {
        int i = 0;
        int j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == s.length();
    }
}
