/**
 * 给定一个表示 大整数 的整数数组 digits，其中 digits[i] 是整数的第 i 位数字。这些数字按从左到右，从最高位到最低位排列。这个大整数不包含任何前导 0。
 * <p>
 * 将大整数加 1，并返回结果的数字数组。
 */
public class Code066_PlusOne {
    /**
     * 不是最优解
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        int add = 1;
        int N = digits.length;
        for (int i = N - 1; i >= 0; i--) {
            if (digits[i] + add >= 10) {
                add = (digits[i] + add) / 10;
            } else {
                add = 0;
            }
        }

        if (add > 0) {
            // 有进位
            int[] res = new int[N + 1];
            add = 1;
            for (int i = digits.length - 1; i >= 0; i--) {
                if (digits[i] + add >= 10) {
                    res[i + 1] = (digits[i] + add) % 10;
                    add = (digits[i] + add) / 10;
                } else {
                    res[i + 1] = digits[i] + add;
                    add = 0;
                }
            }
            res[0] = add;
            return res;
        } else {
            // 没有进位
            int[] res = new int[N];
            add = 1;
            for (int i = digits.length - 1; i >= 0; i--) {
                if (digits[i] + add >= 10) {
                    res[i] = (digits[i] + add) % 10;
                    add = (digits[i] + add) / 10;
                } else {
                    res[i] = digits[i] + add;
                    add = 0;
                }
            }
            return res;
        }
    }

    /**
     * 下一个数字
     *
     * @param digits
     * @return
     */
    public int[] plusOne2(int[] digits) {
        // 找到第一个9所在位置
        // 分情况讨论
        // 第一种情况 9的后面存在非9的数字 最后1位+1
        // 第二种情况  不存在 向前找第一个非9的数字 该位置+1 该位置后面全部置0
        // 还有一种情况 要补位
        int N = digits.length;
        int index = N - 1;
        if (digits[N - 1] < 9) {
            digits[N - 1]++;
            return digits;
        } else {
            // 向前找第一个非9
            while (index >= 0 && digits[index] == 9) {
                index--;
            }
            if (index < 0) {
                // 补0
                int[] ints = new int[N + 1];
                ints[0] = 1;
                return ints;
            } else {
                digits[index++]++;
                while (index < N) {
                    digits[index++] = 0;
                }
                return digits;
            }
        }
    }
}
