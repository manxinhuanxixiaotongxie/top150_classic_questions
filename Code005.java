/**
 * 给你一个字符串 s，找到 s 中最长的 回文 子串。
 */
public class Code005 {
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        char[] str = s.toCharArray();
        int len = str.length;
        int left = 0;
        int right = 0;
        for (int i = 1; i < len - 1; i++) {
            int l = i;
            int r = i;
            while (l >= 0 && r < len && str[l] == str[r]) {
                l--;
                r++;
            }
            // 结算
            if (r - l - 1 > right - left + 1) {
                left = l + 1;
                right = r - 1;
            }
        }
        for (int i = 0; i < str.length; i++) {
            int l = i;
            int r = i + 1;
            while (l >= 0 && r < str.length && str[l] == str[r]) {
                l--;
                r++;
            }
            // 结算
            if (r - l - 1 > right - left + 1) {
                left = l + 1;
                right = r - 1;
            }
        }
        return s.substring(left, right + 1);
    }

    /**
     * manacher算法
     *
     * @param s
     * @return
     */
    public String longestPalindrome2(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        char[] str = getDoubleLen(s.toCharArray());
        // manacher算法
        // 回文半径c 取得回文半径c时的最右侧位置
        int c = 0;
        int r = -1;
        int[] pNext = new int[str.length];
        // 第一种情况
        int i = 0;
        int maxLen = 0;
        int center = 0;
        while (i < str.length) {
            pNext[i] = i < r ? Math.min(pNext[2 * c - i], r - i) : 1;
            while (i + pNext[i] < str.length && i - pNext[i] >= 0) {
                if (str[i + pNext[i]] == str[i - pNext[i]]) {
                    pNext[i]++;
                } else {
                    break;
                }
            }
            if (i + pNext[i] > r) {
                c = i;
                r = i + pNext[i];
            }
            /**
             * 原始下标i -->对应新串下标2i+1
             * 因此：
             * 新串奇数下标就是原始字符
             * 新串偶数下标就是‘#’
             * 证明：
             * （1）pnext[i]-1就是原始回文长度
             * 设新串中以center为中心的回文半径为pnext[center] = p
             * 新串回文覆盖范围[center-p +1,center+p -1],长度为2p-1
             * 在这2p-1个字符中，#和原始字符交替排列：
             * （1）如果center是奇数（原始字符）：序列为  #...原始...#，原始字符有p-1个
             * （2）如果center是偶数(#)：序列为 原始#原始 #有p个，原始字符有p-1个
             * center与maxlen奇偶性相同
             */
            if (pNext[i] - 1 > maxLen) {
                maxLen = pNext[i] - 1;
                center = i;
            }
            i++;
        }
        // 损失精度
        // center = 3 maxLen = 3  这种写法有边界是2  实际的右边界是3
        // 永远不要把“中心坐标”和“半径长度”拆开分别除以 2 之后再相加减。
        /**
         * 在写任何算法题（特别是涉及数组下标、字符串截取）时，如果你需要算一个范围，请牢记这个铁律：
         *
         * 永远不要把“中心坐标”和“半径长度”拆开分别除以 2 之后再相加减。
         *
         * 为什么？
         * 因为只要其中有一个是奇数，拆开除以 2 就会分别“缩水”一次。两个缩水后的数字再相加减，误差就会放大，区间就会错位。
         *
         * 正确的策略永远是：
         * 要么像你正确的那行代码一样，先在分子上把加减法做完（合为一体），最后只除以一次 2：
         * 要么干脆像大部分标准解法一样，只求出准确的 start，然后用 start + maxLen 去算右边界，绝对不要去对右边界做二次除法：
         *
         */
        // 这样写是错的
//        return s.substring(center/2 - maxLen/2,center/2 + maxLen/2);
        // 这样写是对的
        // return s.substring(center/2 - maxLen/2,(center+ maxLen)/2);
        int start = (center - maxLen) / 2;
        return s.substring(start, start + maxLen);
    }

    /**
     * 2倍长度原始字符串
     *
     * @param str
     * @return
     */
    public char[] getDoubleLen(char[] str) {
        int n = str.length;
        char[] ans = new char[(n << 1) + 1];

        for (int i = 0; i < n; i++) {
            ans[2 * i + 1] = str[i];
            ans[2 * i] = '#';
        }
        ans[(2 * n)] = '#';
        return ans;
    }

}
