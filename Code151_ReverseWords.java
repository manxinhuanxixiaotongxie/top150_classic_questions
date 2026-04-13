/**
 * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
 *
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 *
 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
 *
 * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，
 * 单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 *
 * 进阶：如果字符串在你使用的编程语言中是一种可变数据类型，请尝试使用 O(1) 额外空间复杂度的 原地 解法。
 *
 */
public class Code151_ReverseWords {
    public String reverseWords(String s) {
        // 先处理字符串
        if (s == null || s.isEmpty() || s.length() == 1) {
            return s;
        }
        String trim = s.trim();
        String[] split1 = trim.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = split1.length - 1; i >= 0; i--) {
            if (!split1[i].isEmpty()) {
                sb.append(split1[i]).append(" ");
            }
        }
        return sb.toString().trim();
    }

    private void swap(String[] str, int i, int j) {
        String temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }

    public static void main(String[] args) {
        Code151_ReverseWords code151ReverseWords = new Code151_ReverseWords();
        String s = "  hello world!  ";
        String result = code151ReverseWords.reverseWords(s);
        System.out.println(result); // Output: "world! hello"
    }
}
