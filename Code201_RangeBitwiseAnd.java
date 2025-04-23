/**
 * 给你两个整数 left 和 right ，表示区间 [left, right] ，返回此区间内所有数字 按位与 的结果（包含 left 、right 端点）。
 */
public class Code201_RangeBitwiseAnd {

    /**
     * 这个方法有可能会超过时间限制 当位数为1的时候还是进行了每位数的重新计算 得到最终结果
     * 位运算的性质：
     *
     * 汉明距离
     *
     * @param left
     * @param right
     * @return
     */
    public int rangeBitwiseAnd(int left, int right) {
        // left 5  right 7
        // 1001  1010  1011

        // 1000 1001  1010 1011 1100 1101  1110 1111 10000 10001 10010 10011 10100 10101 10110 10111
        // 8     9     10   11    12 13     14    15   16   17     18   19     20   21     22   23
        // 找到right最左侧的1
        if (left == right) {
            return left;
        }

        int i = 31;
        for (; i >= 0; i--) {
            if ((right & (1 << i)) != 0) {
                break;
            }
        }
        // 101 110 111

        if ((left & (1 << i)) == 0) {
            return 0;
        } else {
            // 说明位数是一样的
            // 从left到right进行异或
            int ans = 0;
            for (; i >= 0; i--) {
                // 从i一直到0
                long l = left;
                // 从l 到right这一位都进行与运算
                int temp = 1 << i;
                while (l <= right) {
                    // 当前位
                    temp &= l;
                    l++;
                }
                ans |= temp;
            }
            return ans;
        }
    }

    public int rangeBitwiseAnd2(int m, int n) {
        while (m < n) {
            // 抹去最右边的 1
            n = n & (n - 1);
        }
        return n;
    }

    /**
     * 二进制的数 只要m与n从右侧有左侧 只要有一个位数是0 那么从当前i到最右侧的位数的  相与之后都应该是0
     *
     * @param m
     * @param n
     * @return
     */
    public int rangeBitwiseAnd(int m, int n) {
        int shift = 0;
        // 找到公共前缀
        while (m < n) {
            m >>= 1;
            n >>= 1;
            ++shift;
        }
        return m << shift;
    }


    public static void main(String[] args) {
        Code201_RangeBitwiseAnd code201 = new Code201_RangeBitwiseAnd();
        int left = 9;
        int right = 12;
        System.out.println(code201.rangeBitwiseAnd(left, right)); // 4
    }
}
