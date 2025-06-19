import java.util.LinkedList;

/**
 * 给你字符串 s 和整数 k 。
 * <p>
 * 请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。
 * <p>
 * 英文中的 元音字母 为（a, e, i, o, u）。
 */
public class Code1456 {
    public int maxVowels(String s, int k) {
        if (s == null || s.length() == 0 || s.length() < k || k <= 0) {
            return 0;
        }
        LinkedList<Integer> window = new LinkedList<>();
        int ans = 0;
        int index = 0;
        while (index < k - 1) {
            if (isVowel(s.charAt(index))) {
                window.addFirst(index);
            }
            index++;
        }
        for (; index < s.length(); index++) {
            // 这里用if或者while都是对的
            // index = 5 k = 3 5-3 = 2
            while (!window.isEmpty() && window.getLast() < index - k + 1) {
                window.removeLast();
            }
            if (isVowel(s.charAt(index))) {
                window.addFirst(index);
            }
            // 结算
            ans = Math.max(ans, window.size());

        }
        return ans;
    }

    public boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    public static void main(String[] args) {
        Code1456 code1456 = new Code1456();
        String s = "aeiou";
        int k = 3;
        System.out.println(code1456.maxVowels(s, k)); // 输出 3
    }
}
