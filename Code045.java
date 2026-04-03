/**
 * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置在下标 0。
 *
 * 每个元素 nums[i] 表示从索引 i 向后跳转的最大长度。换句话说，如果你在索引 i 处，你可以跳转到任意 (i + j) 处：
 *
 * 0 <= j <= nums[i] 且
 * i + j < n
 * 返回到达 n - 1 的最小跳跃次数。测试用例保证可以到达 n - 1。
 *
 */
public class Code045 {
    public int jump(int[] nums) {
        int ans = 0;
        int mostRight = 0;
        int mostNextRight = 0;
        for (int i = 0; i < nums.length -1; i++) {
            mostNextRight = Math.max(mostNextRight, i + nums[i]);
            if (i == mostRight) {
                // 需要修桥
                ans++;
                mostRight = mostNextRight;
            }
        }
        return ans;
    }
}
