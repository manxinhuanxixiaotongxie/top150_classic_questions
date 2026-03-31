/**
 * 给你一个字符串 s，找到 s 中最长的 回文 子串。
 */
public class Code005 {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char[] str = s.toCharArray();
        int len = str.length;
        int left = 0;
        int right = 0;
        for (int i = 1; i < len - 1; i++) {
            int l = i;
            int r = i;
            while (l >= 0 && r < len && str[l] == str[r]) {
                l--;
                r++;
            }
            // 结算
            if (r - l - 1 > right - left + 1) {
                left = l + 1;
                right = r - 1;
            }
        }
        for (int i = 0; i < str.length; i++) {
            int l = i;
            int r = i + 1;
            while (l >= 0 && r < str.length && str[l] == str[r]) {
                l--;
                r++;
            }
            // 结算
            if (r - l - 1 > right - left + 1) {
                left = l + 1;
                right = r - 1;
            }
        }
        return s.substring(left, right + 1);
    }

}
