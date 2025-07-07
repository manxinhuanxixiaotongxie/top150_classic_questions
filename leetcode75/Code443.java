package leetcode75;

/**
 * 给你一个字符数组 chars ，请使用下述算法压缩：
 *
 * 从一个空字符串 s 开始。对于 chars 中的每组 连续重复字符 ：
 *
 * 如果这一组长度为 1 ，则将字符追加到 s 中。
 * 否则，需要向 s 追加字符，后跟这一组的长度。
 * 压缩后得到的字符串 s 不应该直接返回 ，需要转储到字符数组 chars 中。需要注意的是，如果组长度为 10 或 10 以上，
 * 则在 chars 数组中会被拆分为多个字符。
 *
 * 请在 修改完输入数组后 ，返回该数组的新长度。
 *
 * 你必须设计并实现一个只使用常量额外空间的算法来解决此问题。
 *
 */
public class Code443 {
    public int compress(char[] chars) {
        int N = chars.length;
        int ans = 0;
        int begin = 0;
        int end = 0;
        while (begin < N) {
            while (end < N && chars[end] == chars[begin]) {
                end ++;
            }
            // 总共有多少个
            int count = end - begin;
            if (begin == end) {
                chars[ans++] = chars[begin++];
                end++;
            }else {
                chars[ans++] = chars[begin];
                if (count > 1) {
                    String countStr = String.valueOf(count);
                    for (int i = 0; i < countStr.length(); i++) {
                        chars[ans++] = countStr.charAt(i);
                    }
                }
                begin = end;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Code443 code443 = new Code443();
        char[] chars = {'a','a', 'b', 'b', 'c', 'c', 'c'};
        int length = code443.compress(chars);
        System.out.println(length);
        for (int i = 0; i < length; i++) {
            System.out.print(chars[i] + " ");
        }
    }
}
