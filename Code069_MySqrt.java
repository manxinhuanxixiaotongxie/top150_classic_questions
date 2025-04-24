/**
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 * <p>
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * <p>
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 * <p>
 * 提示：
 * <p>
 * 0 <= x <= 2^31 - 1
 */
public class Code069_MySqrt {
    public int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        int left = 0;
        while (left <= x / 2 && left * left <= x) {
            left++;
        }
        return left - 1;
    }

    public int mySqrt2(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        int left = 0;
        int right = x / 2;
        int ans = left;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid * mid == x) {
                return mid;
            } else if ((long) mid * mid < x) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    /**
     * 二分
     *
     * @param x
     * @return
     */
    public int mySqrt3(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        int left = 0;
        int right = x / 2;
        int ans = left;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid * mid <= x) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        Code069_MySqrt code069 = new Code069_MySqrt();
        int x = 2147395600;
        System.out.println(code069.mySqrt(x));
    }
}
