/**
 * 给定一个整数 n ，返回 n! 结果中尾随零的数量。
 * <p>
 * 提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
 * <p>
 * <p>
 * 进阶：你可以设计并实现对数时间复杂度的算法来解决此问题吗？
 */
public class Code172 {
    public int trailingZeroes(int n) {
        // 25 2
        int ans = 0;
        while (n / 5 > 0) {
            ans += n / 5;
            n /= 5;
        }
        return ans;
    }
}
