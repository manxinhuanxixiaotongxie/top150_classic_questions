/**
 * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中 最后一个 单词的长度。
 * <p>
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 */
public class Code058_LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        char[] str = s.toCharArray();
        int res = 0;
        boolean flag = false;
        for (int i = str.length - 1; i >= 0; i--) {
            if (str[i] != ' ') {
                flag = true;
            }
            if (flag) {
                if (str[i] == ' ') {
                    break;
                } else {
                    res++;
                }
            }
        }
        return res;
    }
}
