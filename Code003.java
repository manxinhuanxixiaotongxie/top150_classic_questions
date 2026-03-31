import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
 *
 */
public class Code003 {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Set<Character> set = new HashSet<>();
        // 辅助结构
        char[] str = s.toCharArray();
        int ans = 1;
        int end = 0;
        for (int i = 0; i < str.length;i++) {
            while (end < str.length && !set.contains(str[end])) {
                set.add(str[end]);
                end++;
            }
            ans = Math.max(ans, end - i);
            set.remove(str[i]);
        }
        return ans;
    }
}
