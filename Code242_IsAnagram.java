import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的 字母异位词。
 *
 * 字母异位词是通过重新排列不同单词或短语的字母而形成的单词或短语，并使用所有原字母一次。
 */
public class Code242_IsAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] help = new int[26];
        for (int i = 0; i < s.length(); i++) {
            help[s.charAt(i) - 'a']++;
            help[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (help[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        Code242_IsAnagram code242IsAnagram = new Code242_IsAnagram();
        System.out.printf(String.valueOf(code242IsAnagram.isAnagram(s,t)));
    }
}
