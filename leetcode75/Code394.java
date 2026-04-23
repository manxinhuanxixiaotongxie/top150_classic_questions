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
                start = innerInfo.index + 1; // 更新start到右括号之后
            }
        }
        return new Info(sb, start);
    }

    class Info {
        StringBuilder sb;
        int index;

        public Info(StringBuilder sb, int index) {
            this.sb = sb;
            this.index = index;
        }
    }

    // 使用嵌套结构
    public String decodeString2(String s) {
        return process2(s.toCharArray(), 0).sb.toString();
    }

    public Info process2(char[] str, int index) {
        int num = 0;
        StringBuilder sb = new StringBuilder();
        while (index < str.length && str[index] != ']') {
            // 如果当前字符是数字 进行计数
            char curStr = str[index];
            if (isDigit(curStr)) {
                // 当前是数字
                num = num * 10 + (str[index++] - '0');
                continue;
            }
            // 如果是[
            if (curStr == '[') {
                // 开始递归
                Info info = process2(str, index + 1);
                // 已经拿到了括号里面的内容
                for (int i = 0; i < num; i++) {
                    // 总共有这么多个
                    sb.append(info.sb);
                }
                index = info.index + 1;
                num = 0;
                continue;
            }
            if (isLetter(curStr)) {
                // 字母
                sb.append(curStr);
                index++;
            }
        }
        return new Info(sb, index);
    }


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
