package leetcode75;

public class Code1318 {
    /**
     * 有问题 待分析
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
            if (bitC == 0) {
                if (((a & b) & (1<< i)) != 0) {
                    // a & b ==1
                    ans += 2;
                } else if (((a | b) & (1 << i)) != 0) {
                    // a | b ==1
                    ans += 1;
                }
            }else {
                if (((a | b) & (1 << i)) != 0) {
                    ans += 1;
                }
                if (((a & b) &(1<< i)) == 0) {
                    // a | b ==0
                    ans += 1;
                }
            }
        }
        return ans;
    }
}
