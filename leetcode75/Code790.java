package leetcode75;

public class Code790 {
    /**
     * 找规律
     * 2*n的矩阵
     *
     * @param n
     * @return
     */
    public int numTilings(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (n == 3) {
            return 5;
        }
        return process(n);
    }

    /**
     * 暴力递归
     * <p>
     * 超时
     *
     * @param n
     * @return
     */
    public int process(int n) {
        if (n < 0) return 0;
        if (n == 0) return 1; // 空矩阵
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 5;
        // 第一种情况
        int ans = 0;
        // 一个竖线占据第一列
        ans += process(n - 1) * 2 % 1000000007;
        // 第二种情况 两个横线占据第一列与第二列
        ans += process(n - 3) % 1000000007;

        /**
         * 分情况讨论：
         * 1，第一种情况
         * 一个竖线占据第一列 总共有 process(n-1)种方法
         *
         * 2.第二种情况
         * 两个横线占据前两列 总共有 process(n-2)种方法
         *
         * 3.第三种情况
         * 3.1 两个L形相互耦合占据前三列 2*process(n-3)种方法
         * 3.2 两个L形状与一个竖线占据占据前四列 2* process(n-4)种方法
         * 3.3 两个L形状与两个横线占据前五列 2* process(n-5)种方法
         */

        return ans % 1000000007;
    }

    public int numTilings2(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (n == 3) {
            return 5;
        }
        return process2(n);
    }

    /**
     * 暴力递归
     * <p>
     * 超时
     *
     * @param n
     * @return
     */
    public int process2(int n) {
        if (n < 0) return 0;
        if (n == 0) return 1; // 空矩阵
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 5;
        // 第一种情况
        int ans = 0;
        // 第一种情况 一个竖线占据第一列
        ans += process2(n - 1) % 1000000007;
        // 第二种情况 两个横线占据第一列与第二列
        ans += process2(n - 2) % 1000000007;
//        ans += process2(n - 3) * 2 % 1000000007;
//        ans += process2(n - 4) * 2 % 1000000007;

//        ans += process2(n - 5) * 2 % 1000000007;
        // 考虑L形状的情况
        // L形状的子问题
        // n-3 两个L与两个横线交叉
        // n-4 两个L与一个横线交叉
        // n-5 两个L与两个横线交叉
        // n-6 两个L与三个横线交叉
        // n-7 两个L与四个横线交叉
        // ...
        // 要覆盖所有的L形状的情况
        // 遍历所有的横线
        // 两个L 与0个横线交叉 n-3
        // 两个L 与1个横线交叉 n-4
        // 最多有多少个横线 n-3个横线
        for (int i = 0; i <= n - 3; i++) {
            ans = (ans % 1000000007 + 2 * process2(i) % 1000000007) % 1000000007;
        }

        /**
         * 化简：
         * f(3) = f(3-1) + f(3-2) + 2 * f(3-3)
         * f(4) = f(4-1) + f(4-2) + 2 * f(4-3) + 2 * f(4-4)
         * f(5) = f(5-1) + f(5-2) + 2 * f(5-3) + 2 * f(5-4) + 2 * f(5-5)
         * f(5) = f(4) + f(3) + 2 * f(2) + 2 * f(1) + 2 * f(0)
         * f(5) =
         * ...
         * f(n) = 2 & f(n-1) + f(n - 3)
         * 推导这个公式：
         * f(n) = f(n-1) + f(n-2) + 2 * f(n-3) + 2 * f(n-4) + ... + 2 * f(0)
         * f(n-1) = f(n-2) + f(n-3) + 2 * f(n-4) + 2 * f(n-5) + ... + 2 * f(0)
         *
         * f(n) -f(n-1) = f(n-1) + f(n-3)
         * 因此：对于每一步骤的计算都可以用上一步骤的结果
         * f(n) = 2* f(n-1) + f(n-3)
         *
         */

        /**
         * 分情况讨论：
         * 1，第一种情况
         * 一个竖线占据第一列 总共有 process(n-1)种方法
         *
         * 2.第二种情况
         * 两个横线占据前两列 总共有 process(n-2)种方法
         *
         * 3.第三种情况
         * 3.1 两个L形相互耦合占据前三列 2*process(n-3)种方法
         * 3.2 两个L形状与一个竖线占据占据前四列 2* process(n-4)种方法
         * 3.3 两个L形状与两个横线占据前五列 2* process(n-5)种方法
         * 3.4 两个L形状与三个横线占据前六列 2* process(n-6)种方法
         * 3.5 两个L形状与四个横线占据前七列 2* process(n-7)种方法
         * 3.6 ...
         * 3.7 两个L形状与n-3个横线占据前n列 2* process(0)种方法
         */

        return ans % 1000000007;
    }

    /**
     * 将process改成动态规划
     *
     * @param n
     * @return
     */
    public int numTilings3(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        long[] dp = new long[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = (2 * dp[i - 1] + dp[i - 3]) % 1000000007;
        }
        return (int) dp[n];
    }

    /**
     * 矩阵快速幂
     */
    public int numTilings4(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        long[][] base = {
                {2, 0, 1},
                {1, 0, 0},
                {0, 1, 0}
        };
        long[][] fod = {{2}, {1}, {1}}; // f(2), f(1), f(0)
        long[][] res = matrixMulNum(base, n - 2);
//        return (int) (res[0][0] * fod[0] % 1000000007 + res[0][1] * fod[1] % 1000000007 + res[0][2] * fod[2] % 1000000007) % 1000000007;、
        // return (int) matrixMul(fod,res)[0][0];
        // 这个写法也是错误的 为什么？
        return (int) matrixMul(res,fod)[0][0];
    }

    // 数组的N次方
    public long[][] matrixMulNum(long[][] matrix, int n) {
        long[][] ans = new long[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        long[][] temp = matrix;
        while (n != 0) {
            if ((n & 1) != 0) {
                ans = matrixMul(ans, temp);
            }
            temp = matrixMul(temp, temp);
            n >>= 1;
        }
        return ans;
    }

    public long[][] matrixMul(long[][] martrix1, long[][] matrix2) {
        // 两个数组相乘
        long[][] res = new long[martrix1.length][matrix2[0].length];
        // 行
        for (int i = 0; i < res.length; i++) {
            // 列
            for (int j = 0; j < matrix2[0].length; j++) {
                // 当前行列的值计算
                // 当前行
                for (int k = 0; k < martrix1[0].length; k++) {
                    //    res[i][j] += martrix1[i][k] * matrix2[k][j] % 1000000007;
                    // 上面这种写法是错误的 为什么？
                    // 因为 res[i][j] += martrix1[i][k] * matrix2[k][j] % 1000000007
                    // 可能会导致 res[i][j] 超过 long 的范围
                    // 所以要把每一步的结果都对 1000000007 取模
                    res[i][j] = (res[i][j] +  martrix1[i][k] * matrix2[k][j]) % 1000000007;
                }
            }
        }
        return res;
    }

    private static final int MOD = 1_000_000_007;

    public int numTilings5(int n) {
        if (n == 1) {
            return 1;
        }
        long[][] f2 = {{2}, {1}, {1}};
        long[][] m = {
                {2, 0, 1},
                {1, 0, 0},
                {0, 1, 0},
        };
        long[][] fn = powMul(m, n - 2, f2);
        return (int) fn[0][0];
    }

    // a^n * f
    private long[][] powMul(long[][] a, int n, long[][] f) {
        long[][] res = f;
        while (n > 0) {
            if ((n & 1) > 0) {
                res = mul(a, res);
            }
            a = mul(a, a);
            n >>= 1;
        }
        return res;
    }

    // 返回矩阵 a 和矩阵 b 相乘的结果
    private long[][] mul(long[][] a, long[][] b) {
        long[][] c = new long[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int k = 0; k < a[i].length; k++) {
                if (a[i][k] == 0) {
                    continue;
                }
                for (int j = 0; j < b[k].length; j++) {
                    c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }
        return c;
    }


    public static void main(String[] args) {
        Code790 code790 = new Code790();
        System.out.println(code790.numTilings(3)); // 5
        System.out.println(code790.numTilings(4)); // 11
        System.out.println(code790.numTilings(5)); // 24
        System.out.println(code790.numTilings(6)); // 53
        System.out.println(code790.numTilings(7)); // 117
        System.out.println(code790.numTilings(30)); // 258

        System.out.println(code790.numTilings2(3)); // 5
        System.out.println(code790.numTilings2(4)); // 11
        System.out.println(code790.numTilings2(5)); // 24
        System.out.println(code790.numTilings2(6)); // 53
        System.out.println(code790.numTilings2(7)); // 117
        System.out.println(code790.numTilings2(30)); // 312342182

        System.out.println(code790.numTilings3(3)); // 5
        System.out.println(code790.numTilings4(3)); // 11

        System.out.println(code790.numTilings5(3)); // 24
    }
}
