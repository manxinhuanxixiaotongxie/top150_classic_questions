import java.util.ArrayList;
import java.util.List;

public class Code054 {
    public List<Integer> spiralOrder(int[][] matrix) {
        int leftRow = 0;
        int rightRow = matrix.length - 1;
        int leftCol = 0;
        int rightCol = matrix[0].length - 1;
        List<Integer> ans = new ArrayList<>();
        while (leftRow <= rightRow && leftCol <= rightCol) {
            cycle(matrix, leftRow++, leftCol++, rightRow--, rightCol--, ans);
        }
        return ans;
    }

    public void cycle(int[][] matrix, int leftRow, int leftCol, int rightRow, int rightCol, List<Integer> res) {
        // 如果只有一行
        if (leftRow == rightRow && leftCol == rightCol) {
            res.add(matrix[leftRow][leftCol]);
        } else if (leftRow == rightRow) {
            // 一行
            for (int i = leftCol; i <= rightCol; i++) {
                res.add(matrix[leftRow][i]);
            }
        } else if (leftCol == rightCol) {
            // 只有一列
            for (int i = leftRow; i <= rightRow; i++) {
                res.add(matrix[i][leftCol]);
            }
        } else {
            // 开始分圈
            // 上面
            for (int i = leftCol; i < rightCol; i++) {
                res.add(matrix[leftRow][i]);
            }
            // 右侧
            for (int i = leftRow ; i < rightRow; i++) {
                res.add(matrix[i][rightCol]);
            }
            // 下册
            for (int i = rightCol; i > leftCol; i--) {
                res.add(matrix[rightRow][i]);
            }
            for (int i = rightRow; i > leftRow; i--) {
                res.add(matrix[i][leftCol]);
            }
        }
    }

}
