package leetcode75;

/**
 * 对于字符串 s 和 t，只有在 s = t + t + t + ... + t + t（t 自身连接 1 次或多次）时，我们才认定 “t 能除尽 s”。
 * <p>
 * 给定两个字符串 str1 和 str2 。返回 最长字符串 x，要求满足 x 能除尽 str1 且 x 能除尽 str2 。
 */
public class Code1071 {
    public String gcdOfStrings(String str1, String str2) {
        // 从最长开始枚举
        int len1 = str1.length();
        int len2 = str2.length();
        StringBuilder sb = new StringBuilder();
        for (int i = Math.min(len1, len2); i > 0; i--) {
            // 使用最小字符串的最小长度进行枚举
            // 判断从 0-i-1的字符串是否能拼接出来两个字符串
            // 如果要拼接成功 len1的长度与len2的长度必须是i的整数倍
            if (len1 % i == 0 && len2 % i == 0) {
                // 具备能够整除的条件
                String subStr = str1.substring(0, i);
                if (isMSum1(str1, subStr) && isMSum1(str2, subStr)) {
                    // 如果两个字符串都能被subStr拼接出来
                    return subStr;
                }
            }
        }

        return "";
    }

    private boolean isMSum1(String s1, String s2) {
        // s1能够被s2拼接出来
        if (s1.length() % s2.length() == 0) {
            int count = s1.length() / s2.length();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < count; i++) {
                sb.append(s2);
            }
            return sb.toString().equals(s1);
        }
        return false;
    }
}
