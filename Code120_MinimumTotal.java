import java.util.List;

/**
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 * <p>
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * 也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 */
public class Code120_MinimumTotal {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;
        if (triangle.size() == 1) return triangle.get(0).get(0);
        return triangle.get(0).get(0) + process(triangle, 1, 0);
    }

    // 来到第i层
    // 上层取的位置是pre
    public int process(List<List<Integer>> triangle, int i, int pre) {
        if (triangle.size() == i) {
            return 0;
        }
        // 来到多少层
        List<Integer> curLevel = triangle.get(i);
        // 取i位置获得的最小值
        int left = curLevel.get(pre) + process(triangle, i + 1, pre);
        // 取i+1位置获得最小值
        if (pre + 1 >= curLevel.size()) {
            return left; // 如果pre+1超出范围，直接返回left
        }
        int right = curLevel.get(pre + 1) + process(triangle, i + 1, pre + 1);
        // 返回两者的最小值
        return Math.min(left, right);
    }

    /**
     * 改成动态规划
     * 观察暴力递归
     * i的范围是 1-N
     * pre的范围是0-所有层级最大值
     *
     * @param triangle
     * @return
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        if (triangle == null || triangle.isEmpty()) return 0;
        if (triangle.size() == 1) return triangle.get(0).get(0);

        // i的最大值
        int row = triangle.size() + 1;
        // pre的最大值
        int col = Integer.MIN_VALUE;
        for (int i = 0; i < triangle.size(); i++) {
            col = Math.max(col, triangle.get(i).size());
        }
        int[][] dp = new int[row][col + 1];
        // 最后一行全都是0 不需要进行填写
        // 从倒数第二行开始填写
        for (int i = row - 2; i >= 0; i--) {
            List<Integer> curLevel = triangle.get(i);
            for (int pre = 0; pre <= col; pre++) {
                // 普遍位置依赖
                if (pre >= curLevel.size()) {
                    // 如果pre超出当前层级的范围，直接跳过
                    continue;
                }
                dp[i][pre] = curLevel.get(pre) + dp[i + 1][pre];
                if (pre + 1 < curLevel.size()) {
                    // 右边位置依赖
                    dp[i][pre] = Math.min(dp[i][pre], curLevel.get(pre + 1) + dp[i + 1][pre + 1]);
                }
            }
        }
        // 返回最顶层的最小路径和
        return triangle.get(0).get(0) + dp[1][0];
    }

    /**
     * 空间压缩
     *
     * @param triangle
     * @return
     */
    public int minimumTotal3(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;
        if (triangle.size() == 1) return triangle.get(0).get(0);

        // i的最大值
        int row = triangle.size() + 1;
        // pre的最大值
        int col = Integer.MIN_VALUE;
        for (int i = 0; i < triangle.size(); i++) {
            col = Math.max(col, triangle.get(i).size());
        }
        int[] dp = new int[col + 1];
        // 最后一行全都是0 不需要进行填写
        // 从倒数第二行开始填写
        for (int i = row - 2; i >= 1; i--) {
            List<Integer> curLevel = triangle.get(i);
            for (int pre = 0; pre <= col; pre++) {
                // 普遍位置依赖
                if (pre >= curLevel.size()) {
                    // 如果pre超出当前层级的范围，直接跳过
                    continue;
                }
                dp[pre] = curLevel.get(pre) + dp[pre];
                if (pre + 1 < curLevel.size()) {
                    // 右边位置依赖
                    dp[pre] = Math.min(dp[pre], curLevel.get(pre + 1) + dp[pre + 1]);
                }
            }
        }
        // 返回最顶层的最小路径和
        return triangle.get(0).get(0) + dp[0];
    }

    public static void main(String[] args) {
        Code120_MinimumTotal solution = new Code120_MinimumTotal();
        // 测试用例
        List<List<Integer>> triangle = List.of(
                List.of(2),
                List.of(3, 4),
                List.of(6, 5, 7),
                List.of(4, 1, 8, 3)
        );
        int result = solution.minimumTotal(triangle);
        System.out.println("最小路径和: " + result); // 输出结果

        int result2 = solution.minimumTotal2(triangle);
        System.out.println("最小路径和(动态规划): " + result2); // 输出结果
    }
}
