import java.util.Map;

/**
 * 给定两个字符串 s 和 t ，判断它们是否是同构的。
 * <p>
 * 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
 * <p>
 * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，
 * 相同字符只能映射到同一个字符上，字符可以映射到自己本身。
 */
public class Code205_IsIsomorphic {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Character> sMap = new java.util.HashMap<>();
        Map<Character, Character> tMap = new java.util.HashMap<>();
        sMap.put(s.charAt(0), t.charAt(0));
        tMap.put(t.charAt(0), s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            if (!sMap.containsKey(s.charAt(i))) {
                sMap.put(s.charAt(i), t.charAt(i));
            } else if (sMap.get(s.charAt(i)) != t.charAt(i)) {
                return false;
            }
            if (!tMap.containsKey(t.charAt(i))) {
                tMap.put(t.charAt(i), s.charAt(i));
            } else if (tMap.get(t.charAt(i)) != s.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Code205_IsIsomorphic code205 = new Code205_IsIsomorphic();
        System.out.println(code205.isIsomorphic("egg", "add")); // true
        System.out.println(code205.isIsomorphic("foo", "bar")); // false
        System.out.println(code205.isIsomorphic("paper", "title")); // true
        System.out.println(code205.isIsomorphic("ab", "aa")); // false
    }
}
