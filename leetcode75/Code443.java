package leetcode75;

/**
 * 给你一个字符数组 chars ，请使用下述算法压缩：
 * <p>
 * 从一个空字符串 s 开始。对于 chars 中的每组 连续重复字符 ：
 * <p>
 * 如果这一组长度为 1 ，则将字符追加到 s 中。
 * 否则，需要向 s 追加字符，后跟这一组的长度。
 * 压缩后得到的字符串 s 不应该直接返回 ，需要转储到字符数组 chars 中。需要注意的是，如果组长度为 10 或 10 以上，
 * 则在 chars 数组中会被拆分为多个字符。
 * <p>
 * 请在 修改完输入数组后 ，返回该数组的新长度。
 * <p>
 * 你必须设计并实现一个只使用常量额外空间的算法来解决此问题。
 */
public class Code443 {
    /**
     * 滑动窗口
     *
     * @param chars
     * @return
     */
    public int compress(char[] chars) {
        int N = chars.length;
        int ans = 0;
        int index = 0;
        while (index < N) {
            // 滑动窗口
            int end = index;
            while (end < N - 1 && chars[end] == chars[end + 1]) {
                end++;
            }
            // end来到了相等的最后一个位置
            // 个数
            int num = end - index + 1;
            if (num == 1) {
                chars[ans++] = chars[index++];
            } else {
                // 不为1 说明重复大于1
                chars[ans++] = chars[index];
                String string = String.valueOf(num);
                for (int i = 0; i < string.length(); i++) {
                    chars[ans++] = string.charAt(i);
                }
                index = end + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Code443 code443 = new Code443();
        char[] chars = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        int length = code443.compress(chars);
        System.out.println(length);
        for (int i = 0; i < length; i++) {
            System.out.print(chars[i] + " ");
        }
    }
}
