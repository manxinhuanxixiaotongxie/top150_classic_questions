package leetcode75;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
 * <p>
 * 提示：
 * <p>
 * 0 <= n <= 10^5
 * <p>
 * 进阶：
 * <p>
 * 很容易就能实现时间复杂度为 O(n log n) 的解决方案，你可以在线性时间复杂度 O(n) 内用一趟扫描解决此问题吗？
 * 你能不使用任何内置函数解决此问题吗？（如，C++ 中的 __builtin_popcount ）
 *
 */
public class Code338 {

    /**
     *
     * n*log(n)解法
     *
     * @param n
     * @return
     */
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int temp = i;
            while (temp != 0) {
                ans[i] += temp & 1;
                temp >>= 1;
            }
        }
        return ans;
    }

    /**
     * 也是n*log(n)解法
     *
     * @param n
     * @return
     */
    public int[] countBits2(int n) {
        int[] ans = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int temp = i;
            int count = 0;
            while (temp != 0) {
                temp -= (temp & (~temp + 1));
                count++;
            }
            ans[i] = count;
        }
        return ans;
    }

    /**
     * 进阶解法
     * 官解
     *
     *
     * @param n
     * @return
     */
    public int[] countBits3(int n) {
        int[] bits = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            bits[i] = bits[i & (i - 1)] + 1;
        }
        return bits;
    }

}
