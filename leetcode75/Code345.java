package leetcode75;

import java.util.List;

public class Code345 {
    public String reverseVowels(String s) {
        if (s == null || s.length() == 0) return s;
        char[] chars = s.toCharArray();
        List<Character> vowels = List.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
        int l = 0;
        int r = chars.length - 1;
        while (l < r) {
            // 两者都是元音
            if (vowels.contains(chars[l]) && vowels.contains(chars[r])) {
                swap(chars, l++, r--);
            } else if (vowels.contains(chars[l])) {
                r--;
            } else if (vowels.contains(chars[r])) {
                l++;
            } else {
                l++;
                r--;
            }
        }
        return new String(chars);
    }

    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
