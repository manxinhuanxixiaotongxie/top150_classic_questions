/**
 * 给定一个 m x n 的整数数组 grid。一个机器人初始位于 左上角（即 grid[0][0]）。机器人尝试移动到 右下角（即 grid[m - 1][n - 1]）。
 * 机器人每次只能向下或者向右移动一步。
 * <p>
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。机器人的移动路径中不能包含 任何 有障碍物的方格。
 * <p>
 * 返回机器人能够到达右下角的不同路径数量。
 * <p>
 * 测试用例保证答案小于等于 2 * 109。
 */
public class Code063_UniquePathsWithObstacles {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }

        return process(obstacleGrid, obstacleGrid.length - 1, obstacleGrid[0].length - 1, 0, 0);
    }


    public int process(int[][] obstacleGrid, int row, int col, int curRow, int curCol) {

        if (curRow > row || curCol > col) {
            return 0; // 越界
        }

        if (curRow == row && curCol == col && obstacleGrid[curRow][curCol] == 0) {
            return 1;
        }

        if (obstacleGrid[curRow][curCol] == 1) {
            return 0;
        }

        if (curRow == row) {
            // 向右走
            return process(obstacleGrid, row, col, curRow, curCol + 1);
        } else if (curCol == col) {
            // 向下走
            return process(obstacleGrid, row, col, curRow + 1, curCol);
        } else {
            // 向右走或者向下走
            return process(obstacleGrid, row, col, curRow + 1, curCol) +
                    process(obstacleGrid, row, col, curRow, curCol + 1);
        }
    }

    /**
     * 改成动态规划
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        int row = obstacleGrid.length - 1;
        int col = obstacleGrid[0].length - 1;
        if (obstacleGrid[row][col] == 0) {
            dp[row][col] = 1;
        }
        // 填充最后一行
        for (int curCol = col - 1; curCol >= 0; curCol--) {
            dp[row][curCol] = obstacleGrid[row][curCol] == 1 ? 0 : dp[row][curCol + 1];
        }

        for (int curRow = row - 1; curRow >= 0; curRow--) {
            dp[curRow][col] = obstacleGrid[curRow][col] == 1 ? 0 : dp[curRow + 1][col];
            for (int curCol = col - 1; curCol >= 0; curCol--) {
                if (obstacleGrid[curRow][curCol] == 1) {
                    dp[curRow][curCol] = 0; // 有障碍物
                } else {
                    dp[curRow][curCol] = dp[curRow + 1][curCol] + dp[curRow][curCol + 1];
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        Code063_UniquePathsWithObstacles code063 = new Code063_UniquePathsWithObstacles();
        int[][] obstacleGrid = {
                {0, 0},
                {0, 1}

        };
        System.out.println(code063.uniquePathsWithObstacles(obstacleGrid)); // 输出：2
    }
}
