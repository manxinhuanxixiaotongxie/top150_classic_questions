package leetcode75;

public class Code1318 {
    /**
     * 有问题 待分析
     *
     * 出现问题的原因：
     * 在讨论c==1 的时候 有且仅有 a b同时为0才需要考虑
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int minFlips(int a, int b, int c) {
        // b & c 0100
        //     a 0010
        //       1101
        int ans = 0;
        // 第一种情况
        // c的当前位数是0
        // a & b ==1 ans + 2
        // a| b ==1 ans + 1
        // c的当前位数是1
        // a | b ==0  ans +1
        // a & b ==0  ans +1
        for (int i = 0; i < 32; i++) {
            int bitC = c & (1 << i);
            // 讨论情况 c 当前位是0
            if (bitC == 0) {
                if (((a & b) & (1 << i)) != 0) {
                    // a & b ==1
                    ans += 2;
                } else if (((a | b) & (1 << i)) != 0) {
                    // a | b ==1
                    ans += 1;
                }
            } else {
                // c == 1 a b 均是0 需要翻转一次
                // 这种写法会算多
//                if (((a & b) & (1 << i)) != (1 << i)) {
//                    // a | b ==0
//                    ans += 1;
//                }
                // 必须要保证 a b当前位都是0
                if (((a | b) & (1 << i)) != (1 << i)) {
                    // a | b ==0
                    ans += 1;
                }
            }
        }
        return ans;
    }

    /**
     * AC 最优解
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int minFlips2(int a, int b, int c) {
        // b & c 0100
        //     a 0010
        //       1101
        int ans = 0;

        for (int i = 0; i < 32; i++) {
            // 对每一位进行处理
            // 第一种情况 当前位 a b 都是1 但是c的当前位是0 翻转两次
            // 第二种情况 当前位a b 都是0 但是c的当前位是1 翻转一次
            // 第三种情况 当前位a b 有一个是1 但是c的当前位是0 翻转一次
            if ((a & (1 << i)) != 0 && ((b & (1 << i)) != 0)) {
                // 当前位都是1
                if ((c & (1 << i)) == 0) {
                    ans += 2;
                }
            } else if ((a & (1 << i)) != 0 || ((b & (1 << i)) != 0)) {
                if ((c & (1 << i)) == 0) {
                    // 当前位有一个是1
                    ans += 1;
                }
            } else {
                // 当前位都是0
                if ((c & (1 << i)) != 0) {
                    ans += 1;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Code1318 code1318 = new Code1318();
        int a = 2;
        int b = 6;
        int c = 5;
        System.out.println(code1318.minFlips(a, b, c)); // 输出 3
        System.out.println(code1318.minFlips2(a, b, c)); // 输出 3
    }
}
