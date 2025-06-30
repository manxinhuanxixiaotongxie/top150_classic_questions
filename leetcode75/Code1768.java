package leetcode75;

/**
 * 交替合并字符串
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
}
