package leetcode75;

import java.util.ArrayList;
import java.util.List;

/**
 * 有 n 个有糖果的孩子。给你一个数组 candies，其中 candies[i] 代表第 i 个孩子拥有的糖果数目，和一个整数 extraCandies 表示你所有的额外糖果的数量。
 * <p>
 * 返回一个长度为 n 的布尔数组 result，如果把所有的 extraCandies 给第 i 个孩子之后，他会拥有所有孩子中 最多 的糖果，那么 result[i] 为 true，否则为 false。
 * <p>
 * 注意，允许有多个孩子同时拥有 最多 的糖果数目。
 */
public class Code1431 {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        // 预分配空间
        List<Boolean> ans = new ArrayList<>(candies.length);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < candies.length; i++) {
            max = Math.max(max, candies[i]);
        }
        for (int i = 0; i < candies.length; i++) {
            if (candies[i] + extraCandies >= max) {
                ans.add(true);
            } else {
                ans.add(false);
            }
        }

        return ans;
    }
}
