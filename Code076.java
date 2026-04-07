import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个字符串 s 和 t，长度分别是 m 和 n，返回 s 中的 最短窗口 子串，
 * 使得该子串包含 t 中的每一个字符（包括重复字符）。如果没有这样的子串，返回空字符串 ""。
 * <p>
 * 测试用例保证答案唯一。
 *
 */
public class Code076 {
    public String minWindow(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0 || t.length() > s.length()) return "";
        // 滑动窗口
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        int[] help = new int[128];
        for (char c : str2) {
            help[c - 'A']++;
        }
        int len1 = s.length();
        int len2 = t.length();
        int index = 0;
        int end = 0;
        int left = 0;
        int right = len1;
        boolean found = false;

        while (index <= (len1 - len2)) {
            while (end < len1 && !isFull(help)) {
                help[str1[end++] - 'A']--;
            }
            if (isFull(help)) {
                if (!found || end - index < right - left) {
                    found = true;
                    right = end;
                    left = index;
                }
            } else {
                // end == len1 且还不满足，右边界到头，直接退出
                break;
            }
            // 出窗口：字符离开窗口，需要还回去
            help[str1[index++] - 'A']++;
        }
        return found ? s.substring(left, right) : "";
    }

    public boolean isFull(int[] help) {
        // 26的大小长度
        for (int i = 0; i < 128; i++) {
            if (help[i] > 0) return false;
        }
        return true;
    }

    static void main() {
        Code076 code = new Code076();
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(code.minWindow(s, t));
    }
}
