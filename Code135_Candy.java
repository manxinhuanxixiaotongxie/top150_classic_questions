import java.util.Arrays;

/**
 * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 * <p>
 * 你需要按照以下要求，给这些孩子分发糖果：
 * <p>
 * 每个孩子至少分配到 1 个糖果。
 * 相邻两个孩子评分更高的孩子会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 */
public class Code135_Candy {
    public int candy(int[] ratings) {
        int[] left = new int[ratings.length];
        int[] right = new int[ratings.length];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        // 寻找递增的部分
        for (int i = 1; i < ratings.length; i++)
            if (ratings[i] > ratings[i - 1]) left[i] = left[i - 1] + 1;
        int count = left[ratings.length - 1];
        // 反向寻找递减的部分
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) right[i] = right[i + 1] + 1;
            count += Math.max(left[i], right[i]);
        }
        return count;
    }

    public int candy2(int[] ratings) {
        int n = ratings.length;
        // 一个数组的方式
        int[] help = new int[n];
        // 每个孩子一个糖果
        Arrays.fill(help, 1);
        // 正向
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                help[i] = help[i - 1] + 1;
            }
        }

        // 逆向
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                help[i] = Math.max(help[i], help[i + 1] + 1);
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += help[i];
        }
        return ans;
    }

    /**
     * 时间复杂度O(n) 空间复杂度O(1）的做法
     *
     * 分组循环
     *
     *
     * @param ratings
     * @return
     */
    public int candy3(int[] ratings) {
        int n = ratings.length;
        int ans = n;
        // 不需要辅助数组
        for (int i = 0; i < n; i++) {
            // 因为峰底位置不需要额外的糖果 因此 在峰底的下一个位置一定需要一个糖果
            int start = i > 0 && ratings[i - 1] < ratings[i] ? i - 1 : i;

            while (i + 1 < n && ratings[i] < ratings[i + 1]) {
                i++;
            }
            // 此时i来到了峰值
            // 那么这时候我们糖果分配方案应该是
            // 从start到i位置 依次递增 start 分配 0 1 2 3 ---i 这样的分配方案
            // 这样的分配方案消耗额外的糖果是(已经事先给每个用户分配了一个糖果) 3*（3-1）个
            int top = i;

            while (i + 1 < n && ratings[i] > ratings[i + 1]) {
                i++;
            }

            int inc = top - start;
            // 从top到i位置是依次递减的
            int dec = i - top;
            // 单独结算峰顶位置 Math.max(inc, dec)
            ans += (inc * (inc - 1) + dec * (dec - 1)) / 2 + Math.max(inc, dec);

        }
        return ans;
    }

}
