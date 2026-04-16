/**
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 */
public class Code221_MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        return maxSide * maxSide;
    }



    public int maximalSquare2(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        // 第0行
        int[][] dp = new int[row][col];
        dp[0][0] = matrix[0][0] == '1' ? 1 : 0;
        int ans = dp[0][0];
        // 第0行填充
        for (int i = 1; i < col; i++) {
            dp[0][i] = matrix[0][i] == '1' ? 1 : 0;
            ans = Math.max(ans, dp[0][i]);
        }
        // 第0列填充
        for (int i = 1; i < row; i++) {
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
            ans = Math.max(ans, dp[i][0]);
        }
        // 普遍位置
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == '0') {
                    continue;
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        return ans * ans;
    }
}
