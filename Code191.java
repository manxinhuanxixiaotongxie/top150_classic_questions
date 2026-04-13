/**
 * 给定一个正整数 n，编写一个函数，获取一个正整数的二进制形式并返回其二进制表达式中 设置位 的个数（也被称为汉明重量）。
 *
 * 进阶：
 *
 * 如果多次调用这个函数，你将如何优化你的算法？
 *
 */
public class Code191 {
    public int hammingWeight(int n) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                ans++;
            }
        }

        return ans;
    }
}
