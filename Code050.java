/**
 * 实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，x^n ）。
 *
 */
public class Code050 {
    public double myPow(double x, int n) {
        boolean negative = n < 0;
        long N = n;
        if (n < 0) {
            N = -N;
        }
        double base = x;
        double ans = 1;
        while (N != 0) {
            if ((N & 1) != 0) {
                ans *= base;
            }
            base *= base;
            N = (N >> 1);
        }
        return negative ? 1/ans : ans;
    }

    static void main() {
        double x = 2.0;
        int n = -2;
        Code050 obj = new Code050();
        System.out.println(obj.myPow(x, n));
    }
}
