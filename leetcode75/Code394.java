package leetcode75;

/**
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * <p>
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * <p>
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * <p>
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 */
public class Code394 {
    public String decodeString(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] str = s.toCharArray();
        int left = 0, right = str.length - 1;
        Info process = process(str, 0);
        return process.sb.toString();
    }

    public Info process(char[] str, int start) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (start < str.length && str[start] != ']') {
            if (isDigit(str[start])) {
                count = count * 10 + (str[start++] - '0');
            } else if (isLetter(str[start])) {
                sb.append(str[start++]);
            } else if (str[start] == '[') {
                start++; // 跳过左括号
                Info innerInfo = process(str, start);
                for (int i = 0; i < count; i++) {
                    sb.append(innerInfo.sb);
                }
                count = 0; // 重置计数器
                start = innerInfo.count + 1; // 更新start到右括号之后
            }
        }
        return new Info(sb, start);
    }

    class Info {
        StringBuilder sb;
        int count;

        public Info(StringBuilder sb, int count) {
            this.sb = sb;
            this.count = count;
        }
    }

//    public String process(char[] str, int left, int right) {
//        if (left == right) {
//            return String.valueOf(str[left]);
//        }
//        StringBuilder sb = new StringBuilder();
//        int count = 0;
//
//        for (int i = left; i <= right;) {
//            // 总共有几种情况
//            // 第一种情况 当前字符是字符
//            if (isLetter(str[i])) {
//               sb.append(str[i++]);
//            }
//            // 第二种情况 当前字符是数字
//            else if (isDigit(str[i])) {
//                count = count * 10 + (str[i++] - '0');
//            }
//            // 第三种情况 当前字符是左括号
//            else if (str[i] == '[') {
//                int start = ++i;
//                StringBuilder inner = new StringBuilder();
//                while (start < right && str[start] != ']') {
//                    inner.append(str[start++]);
//                }
//                // 从left到index-1范围字符串是重复字符串
//                for (int times = 0; times < count; times++) {
//                    sb.append(inner);
//                }
//                sb.append(process(str, i + 1, right)); // 处理右括号之后的字符串
//                count = 0; // 重置计数器
//                i = start + 1; // 移动到右括号之后
//            }
//            // 第四种情况 当前字符是右括号
//            // 这个函数的含义是 从left-right之前字符串
//        }
//        return sb.toString();
//    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    public static void main(String[] args) {
        Code394 code394 = new Code394();
        String s = "3[a2[c]]";
        String result = code394.decodeString(s);
        System.out.println(result); // 输出 "accaccacc"
    }
}
