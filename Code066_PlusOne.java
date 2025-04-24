/**
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * <p>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * <p>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 */
public class Code066_PlusOne {
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
}
