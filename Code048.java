/**
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * <p>
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 */
public class Code048 {
    /**
     * 分圈结构
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int leftRow = 0;
        int leftCol = 0;
        int rightRow = row - 1;
        int rightCol = col - 1;
        while (leftRow < rightRow && leftCol < rightCol) {
            cycle(matrix, leftRow++, leftCol++, rightRow--, rightCol--);
        }
    }

    public void cycle(int[][] matrix, int leftRow, int leftCol, int rightRow, int rightCol) {
        // 总共有多少个数进行交换
        int times = rightCol - leftCol;
        for (int i = 0; i < times; i++) {
            // 顺时针旋转
            int temp = matrix[leftRow][leftCol + i];
            matrix[leftRow][leftCol + i] = matrix[rightRow - i][leftCol];
            matrix[rightRow - i][leftCol] = matrix[rightRow][rightCol - i];
            matrix[rightRow][rightCol - i] = matrix[leftRow + i][rightCol];
            matrix[leftRow + i][rightCol] = temp;
        }
    }

    static void main() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Code048 c = new Code048();
        c.rotate(matrix);
    }
}
