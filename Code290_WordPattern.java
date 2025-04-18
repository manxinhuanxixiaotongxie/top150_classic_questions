import java.util.HashMap;
import java.util.Map;

/**
 * 给定一种规律 pattern 和一个字符串 s ，判断 s 是否遵循相同的规律。
 * <p>
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 s 中的每个非空单词之间存在着双向连接的对应规律。
 * <p>
 * 提示:
 * <p>
 * 1 <= pattern.length <= 300
 * pattern 只包含小写英文字母
 * 1 <= s.length <= 3000
 * s 只包含小写英文字母和 ' '
 * s 不包含 任何前导或尾随对空格
 * s 中每个单词都被 单个空格 分隔
 */
public class Code290_WordPattern {

    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (pattern.length() != words.length) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        Map<String, Character> reverseMap = new HashMap<>();
        map.put(pattern.charAt(0), words[0]);
        reverseMap.put(words[0], pattern.charAt(0));
        for (int i = 1; i < pattern.length(); i++) {
            if (!map.containsKey(pattern.charAt(i))) {
                if (reverseMap.containsKey(words[i])) {
                    return false;
                }
                map.put(pattern.charAt(i), words[i]);
                reverseMap.put(words[i], pattern.charAt(i));
            } else {
                if (!map.get(pattern.charAt(i)).equals(words[i])) {
                    return false;
                }
            }
            if (!reverseMap.containsKey(words[i])) {
                if (map.containsKey(pattern.charAt(i))) {
                    return false;
                }
                reverseMap.put(words[i], pattern.charAt(i));
            } else {
                if (!reverseMap.get(words[i]).equals(pattern.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }
}
