package leetcode75;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import static java.nio.charset.StandardCharsets.ISO_8859_1;


/**
 * 如果可以使用以下操作从一个字符串得到另一个字符串，则认为两个字符串 接近 ：
 * <p>
 * 操作 1：交换任意两个 现有 字符。
 * 例如，abcde -> aecdb
 * 操作 2：将一个 现有 字符的每次出现转换为另一个 现有 字符，并对另一个字符执行相同的操作。
 * 例如，aacabb -> bbcbaa（所有 a 转化为 b ，而所有的 b 转换为 a ）
 * 你可以根据需要对任意一个字符串多次使用这两种操作。
 * <p>
 * 给你两个字符串，word1 和 word2 。如果 word1 和 word2 接近 ，就返回 true ；否则，返回 false
 * <p>
 * word1 = "cabbba", word2 = "abbccc"
 */
public class Code1657 {
    /**
     * 更优雅的实现
     *
     * @param s
     * @param t
     * @return
     */
    public boolean closeStrings(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] sCnt = new int[26];
        for (byte c : s.getBytes(ISO_8859_1)) {
            sCnt[c - 'a']++;
        }

        int[] tCnt = new int[26];
        for (byte c : t.getBytes(ISO_8859_1)) {
            tCnt[c - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if ((sCnt[i] == 0) != (tCnt[i] == 0)) {
                return false;
            }
        }

        Arrays.sort(sCnt);
        Arrays.sort(tCnt);
        return Arrays.equals(sCnt, tCnt);
    }

    /**
     * 我的实现
     *
     * @param word1
     * @param word2
     * @return
     */
    public boolean closeStrings2(String word1, String word2) {
        char[] str1 = word1.toCharArray();
        char[] str2 = word2.toCharArray();
        if (str1.length != str2.length) return false; // 长度不相等
        HashSet<Character> set1 = new HashSet<>();
        Map<Character, Integer> map1 = new HashMap<>();
        for (int i = 0; i < str1.length; i++) {
            set1.add(str1[i]);
            map1.put(str1[i], map1.getOrDefault(str1[i], 0) + 1);
        }
        Map<Character, Integer> map2 = new HashMap<>();
        for (int i = 0; i < str2.length; i++) {
            if (set1.contains(str2[i])) {
                set1.remove(str2[i]);
            }
            map2.put(str2[i], map2.getOrDefault(str2[i], 0) + 1);
        }
        if (set1.size() != 0) return false;
        List<Integer> list1 = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : map1.entrySet()) {
            list1.add(entry.getValue());
        }
        List<Integer> list2 = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : map2.entrySet()) {
            list2.add(entry.getValue());
        }
        if (list1.size() != list2.size()) return false; // 字符种类不相等
        Collections.sort(list1);
        Collections.sort(list2);
        for (int i = 0; i < list1.size(); i++) {
            if (!Objects.equals(list1.get(i), list2.get(i))) {
                return false; // 字符出现次数不相等
            }
        }
        return true;
    }

    /**
     * 使用位运算
     * 更优化的解法
     * @return
     */
    public boolean closeStrings3(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int sMask = 0;
        int tMask = 0;
        int[] sCnt = new int[26];
        int[] tCnt = new int[26];
        for (byte c : s.getBytes(ISO_8859_1)) { // 比 toCharArray 更快
            sMask |= 1 << (c - 'a'); // 记录 s 中有字符 c
            sCnt[c - 'a']++;
        }
        for (byte c : t.getBytes(ISO_8859_1)) {
            tMask |= 1 << (c - 'a'); // 记录 t 中有字符 c
            tCnt[c - 'a']++;
        }

        Arrays.sort(sCnt);
        Arrays.sort(tCnt);
        return sMask == tMask && Arrays.equals(sCnt, tCnt);
    }

    public static void main(String[] args) {
        String word1 = "abc";
        String word2 = "bca";
        Code1657 code1657 = new Code1657();
        boolean result = code1657.closeStrings2(word1, word2);
        System.out.println(result); // 输出 true
    }
}
