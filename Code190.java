import java.util.Arrays;

/**
 * 颠倒给定的 32 位有符号整数的二进制位。
 * <p>
 * 进阶: 如果多次调用这个函数，你将如何优化你的算法？
 *
 */
public class Code190 {
    public int reverseBits(int n) {
        String binaryString = Integer.toBinaryString(n);
        int[] help = new int[32];
        Arrays.fill(help, 0);
        char[] str = binaryString.toCharArray();
        int len = str.length - 1;
        int left = 0;
        while (left < 32) {
            help[left] = left <= len ? str[len - left] - '0' : 0;
            left++;
        }
        int ans = 0;
        int base = 1;
        int index = 0;
        while (index < 32) {
            if (help[31 - index] == 1) {
                ans |= base;
            }
            base <<= 1;
            index++;
        }
        return ans;
    }

    static void main() {
        Code190 code190 = new Code190();
        System.out.println(code190.reverseBits(43261596));
    }
}
