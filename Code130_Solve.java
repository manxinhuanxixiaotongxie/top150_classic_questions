/**
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' 组成，捕获 所有 被围绕的区域：
 * <p>
 * 连接：一个单元格与水平或垂直方向上相邻的单元格连接。
 * 区域：连接所有 'O' 的单元格来形成一个区域。
 * 围绕：如果您可以用 'X' 单元格 连接这个区域，并且区域中没有任何单元格位于 board 边缘，则该区域被 'X'
 * 单元格围绕。
 * 通过 原地 将输入矩阵中的所有 'O' 替换为 'X' 来 捕获被围绕的区域。你不需要返回任何值
 * <p>
 * 提示：
 * <p>
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] 为 'X' 或 'O'
 */
public class Code130_Solve {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;
        UnionSet unionSet = new UnionSet(board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    if (i == 0 || i == board.length - 1 || j == 0 || j == board[0].length - 1) {
                        // 说明是边界的O 指向一个虚拟节点
                        int index1 = unionSet.getIndex(i, j);
                        int index2 = board.length * board[0].length;
                        unionSet.union(index1, index2);
                    } else {
                        // 上
                        if (board[i - 1][j] == 'O') {
                            int index1 = unionSet.getIndex(i, j);
                            int index2 = unionSet.getIndex(i - 1, j);
                            unionSet.union(index1, index2);
                        }
                        // 下
                        if (i < board.length - 1 && board[i + 1][j] == 'O') {
                            int index1 = unionSet.getIndex(i, j);
                            int index2 = unionSet.getIndex(i + 1, j);
                            unionSet.union(index1, index2);
                        }
                        // 左
                        if (board[i][j - 1] == 'O') {
                            int index1 = unionSet.getIndex(i, j);
                            int index2 = unionSet.getIndex(i, j - 1);
                            unionSet.union(index1, index2);
                        }
                        // 右
                        if (j < board[0].length - 1 && board[i][j + 1] == 'O') {
                            int index1 = unionSet.getIndex(i, j);
                            int index2 = unionSet.getIndex(i, j + 1);
                            unionSet.union(index1, index2);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (unionSet.isSameFather(i, j)) {
                    board[i][j] = 'O';
                } else {
                    board[i][j] = 'X';
                }
            }
        }
    }

    class UnionSet {
        int col;
        int[] father;

        UnionSet(char[][] board) {
            col = board[0].length;
            father = new int[board.length * board[0].length + 1];
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    int index = getIndex(i, j);
                    father[index] = index;
                }
            }
            // 构造一个虚拟节点
            father[board.length * board[0].length] = board.length * board[0].length;
        }

        public void union(int index1, int index2) {
            int father1 = findFather(index1);
            int father2 = findFather(index2);
            if (father1 != father2) {
                // 进行合并
                father[father1] = father2;
            }
        }

        public boolean isSameFather(int i1, int j1) {
            int index = getIndex(i1, j1);
            return findFather(index) == findFather(father.length - 1);
        }

        // 转换后的index
        private int findFather(int index) {
            int[] help = new int[father.length];
            int helpIndex = 0;

            while (father[index] != index) {
                // helpIndex记住当前位置
                help[helpIndex++] = index;
                index = father[index];
            }
            // 如果helpIndex等于6 那么意味着有6个数指向了相同的father
            // 0 1 2 3 4 5
            while (--helpIndex >= 0) {
                father[help[helpIndex]] = index;

            }
            return index;
        }

        private int getIndex(int i, int j) {
            return i * col + j;
        }
    }

    public static void main(String[] args) {
        Code130_Solve code130_solve = new Code130_Solve();
        // [["O","O"],["O","O"]]
        char[][] board = {
                {'O', 'O', '0'},
                {'O', 'O', '0'},
                {'O', 'O', '0'},
                {'O', 'O', '0'}
        };

        code130_solve.solve(board);
        for (char[] chars : board) {
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println();
        }
        System.out.println("====================");
    }
}
