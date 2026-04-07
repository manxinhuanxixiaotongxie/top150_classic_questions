/**
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * <p>
 * 说明：每次只能向下或者向右移动一步。
 *
 */
public class Code064 {
    public int minPathSum(int[][] grid) {
        return process(grid, grid.length - 1, grid[0].length - 1, 0, 0);
    }

    public int process(int[][] grid, int row, int col, int curRow, int curCol) {
        if (curRow == row && curCol == col) {
            return grid[row][col];
        } else if (curRow == row) {
            // 只能向右走
            return grid[curRow][curCol] + process(grid, row, col, curRow, curCol + 1);
        } else if (curCol == col) {
            // 只能向下走
            return grid[curRow][curCol] + process(grid, row, col, curRow + 1, curCol);
        } else {
            // 可以向下走
            // 也可以向右走
            return grid[curRow][curCol] + Math.min(process(grid, row, col, curRow + 1, curCol),
                    process(grid, row, col, curRow, curCol + 1));
        }
    }

    /**
     * 改动态规划
     *
     * @param grid
     * @return
     */
    public int minPathSum2(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[][] dp = new int[row][col];
        dp[row - 1][col - 1] = grid[row - 1][col - 1];
        // 最下面一行
        for (int curCol = col - 2; curCol >= 0; curCol--) {
            dp[row - 1][curCol] = grid[row - 1][curCol] + dp[row - 1][curCol + 1];
        }
        // 最右侧一列
        for (int curRow = row - 2; curRow >= 0; curRow--) {
            dp[curRow][col - 1] = grid[curRow][col - 1] + dp[curRow + 1][col - 1];
        }
        // 普遍位置
        for (int curRow = row - 2; curRow >= 0; curRow--) {
            for (int curCol = col - 2; curCol >= 0; curCol--) {
                dp[curRow][curCol] = grid[curRow][curCol] + Math.min(dp[curRow + 1][curCol], dp[curRow][curCol + 1]);
            }
        }
        return dp[0][0];
    }

    /**
     * 空间压缩
     *
     * @param grid
     * @return
     */
    public int minPathSum3(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[] dp = new int[col];
        dp[col - 1] = grid[row - 1][col - 1];
        // 先把第一层进行填写
        for (int curCol = col - 2; curCol >= 0; curCol--) {
            // 依赖右侧的位置
            dp[curCol] = grid[row - 1][curCol] + dp[curCol + 1];
        }
        // 进行滚动更新
        // 总共滚动多少次
        for (int curRow = row - 2; curRow >= 0; curRow--) {
            // 最右侧的数字依赖下面的位置
            // 进行当前行的变更
            dp[col - 1] = grid[curRow][col - 1] + dp[col - 1];
            for (int curCol = col - 2; curCol >= 0; curCol--) {
                int down = dp[curCol];
                dp[curCol] = grid[curRow][curCol] + Math.min(down, dp[curCol + 1]);
            }
        }

        return dp[0];
    }
}
