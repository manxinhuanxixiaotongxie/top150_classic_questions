/**
 * 根据 百度百科 ， 生命游戏 ，简称为 生命 ，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
 *
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态： 1 即为 活细胞 （live），或 0 即为 死细胞 （dead）。
 * 每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 *
 * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 * 下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是 同时 发生的。给你 m x n 网格面板 board 的当前状态，
 * 返回下一个状态。
 *
 * 给定当前 board 的状态，更新 board 到下一个状态。
 *
 * 注意 你不需要返回任何东西。
 *
 *
 */
public class Code289_GameOfLife {
    public static void gameOfLife(int[][] board) {
        int N = board.length;
        int M = board[0].length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int neighbors = neighbors(board, i, j);
                if (neighbors == 3 || (board[i][j] == 1 && neighbors == 2)) {
                    board[i][j] |= 2;
                }
            }
        }
        /**
         * 整数有32位  题目已经使用的只有最后一位 因为细胞要么是0 要么是1
         * 使用倒数第二位的数表示细胞下一个位置的存活情况
         *
         */
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] >>= 1;
            }
        }
    }

    // b[i][j] 这个位置的数，周围有几个1
    public static int neighbors(int[][] b, int i, int j) {
        return f(b, i - 1, j - 1)
                + f(b, i - 1, j)
                + f(b, i - 1, j + 1)
                + f(b, i, j - 1)
                + f(b, i, j + 1)
                + f(b, i + 1, j - 1)
                + f(b, i + 1, j)
                + f(b, i + 1, j + 1);
    }

    // b[i][j] 上面有1，就返回1，上面不是1，就返回0
    public static int f(int[][] b, int i, int j) {
        return (i >= 0 && i < b.length && j >= 0 && j < b[0].length && (b[i][j] & 1) == 1) ? 1 : 0;
    }
}
