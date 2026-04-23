package leetcode75;

public class Code1456 {
    public int maxVowels(String s, int k) {
        char[] str = s.toCharArray();
        int ans = 0;

        int index = 0;
        int n = str.length;
        int count = 0;
        while (index < n) {
            int left = index - k + 1;
            if (isVowel(str[index++])) {
                count++;
            }
            if (left < 0) {
                continue;
            }
            // 结算
            ans = Math.max(ans, count);
            // 小优化 已经找到了长度为k的的窗口 即找到来一个窗口内容全部输出为元音字母 不可能再找到更长的窗口了
            if (ans == k) {
                break;
            }
            if (isVowel(str[left])) {
                count--;
            }

        }
        return ans;
    }

    public boolean isVowel(char c) {
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
            return true;
        }
        return false;
    }
}
