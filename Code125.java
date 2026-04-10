/**
 * 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
 * <p>
 * 字母和数字都属于字母数字字符。
 * <p>
 * 给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
 *
 */
public class Code125 {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        String lowerCase = s.toLowerCase();
        char[] charArray = lowerCase.toCharArray();
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (!Character.isLetter(charArray[left]) && !Character.isDigit(charArray[left])) {
                left++;
            } else if (!Character.isLetter(charArray[right]) && !Character.isDigit(charArray[right])) {
                right--;
            } else if (charArray[left] == charArray[right]) {
                left++;
                right--;
            } else if (charArray[left] != charArray[right]) {
                return false;
            }
        }
        return true;
    }
}
