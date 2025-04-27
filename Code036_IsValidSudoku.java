import java.util.HashSet;

/**
 * 请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 * <p>
 * <p>
 * 注意：
 * <p>
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * 空白格用 '.' 表示。
 */
public class Code036_IsValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length == 0) return true;
        // 检查行有效
        for (int row = 0; row < board.length; row++) {
            // 列
            HashSet<Character> set = new HashSet<>();
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] != '.' && set.contains(board[row][col])) {
                    return false;
                }
                set.add(board[row][col]);
            }
        }
        // 检查列有效
        for (int col = 0; col < board[0].length; col++) {
            // 行
            HashSet<Character> set = new HashSet<>();
            for (int row = 0; row < board.length; row++) {
                if (board[row][col] != '.' && set.contains(board[row][col])) {
                    return false;
                }
                set.add(board[row][col]);
            }
        }
        // 检查3*3有效
        for (int row = 0; row < board.length; row += 3) {
            //进行分组
            for (int col = 0; col < board[0].length; col += 3) {
                HashSet<Character> set = new HashSet<>();
                // 总共有row*col这么多组
                // 每个组的下标
                int rowLimit = row + 3;
                int colLimit = col + 3;
                for (int rowIndex = row; rowIndex < rowLimit; rowIndex++) {
                    for (int colIndex = col; colIndex < colLimit; colIndex++) {
                        if (board[rowIndex][colIndex] != '.' && set.contains(board[rowIndex][colIndex])) {
                            return false;
                        }
                        set.add(board[rowIndex][colIndex]);
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Code036_IsValidSudoku code036 = new Code036_IsValidSudoku();
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'},
        };
        System.out.println(code036.isValidSudoku(board));
    }
}
