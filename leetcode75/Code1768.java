package leetcode75;

/**
 * 交替合并字符串
 * 给你两个字符串 word1 和 word2 。请你从 word1 开始，通过交替添加字母来合并字符串。如果一个字符串比另一个字符串长，就将多出来的字母追加到合并后字符串的末尾。
 *
 * 返回 合并后的字符串 。
 */
public class Code1768 {
    public String mergeAlternately(String word1, String word2) {
        if (word1 == null) return word2;
        if (word2 == null) return word1;
        char[] str1 = word1.toCharArray();
        char[] str2 = word2.toCharArray();
        int l1 = 0;
        int l2 = 0;
        StringBuilder ans = new StringBuilder();
        boolean addWord1 = true;
        while (l1 < str1.length && l2 < str2.length) {
            if (addWord1) {
                ans.append(str1[l1++]);
                addWord1 = false;
            } else {
                ans.append(str2[l2++]);
                addWord1 = true;
            }
        }
        while (l1 < str1.length) {
            ans.append(str1[l1++]);
        }

        while (l2 < str2.length) {
            ans.append(str2[l2++]);
        }

        return ans.toString();
    }

    /**
     * 更优雅的写法
     *
     * @param word1
     * @param word2
     * @return
     */
    public String mergeAlternately2(String word1, String word2) {
        if (word1 == null) return word2;
        if (word2 == null) return word1;
        char[] str1 = word1.toCharArray();
        char[] str2 = word2.toCharArray();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < str1.length || i < str2.length;i++) {
            if (i < str1.length) {
                ans.append(str1[i]);
            }
            if (i < str2.length) {
                ans.append(str2[i]);
            }
        }
        return ans.toString();
    }
}
