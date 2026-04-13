import java.awt.desktop.PreferencesEvent;

/**
 * 岛屿的数量
 * <p>
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * <p>
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 */
public class Code200 {

    public int numIslands(char[][] grid) {
        UnionSet unionSet = new UnionSet(grid);
        // 第一行
        for (int col = 1; col < grid[0].length; col++) {
            if (grid[0][col] == '1' && grid[0][col - 1] == '1') {
                unionSet.union(0, col, 0, col - 1);
            }
        }
        // 第一列  ← Bug1: 原来是 union(0, row, 0, row-1)，row/col 传反了
        for (int row = 1; row < grid.length; row++) {
            if (grid[row][0] == '1' && grid[row - 1][0] == '1') {
                unionSet.union(row, 0, row - 1, 0);
            }
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    // 上 左
                    if (grid[i - 1][j] == '1') {
                        unionSet.union(i, j, i - 1, j);
                    }
                    if (grid[i][j - 1] == '1') {
                        unionSet.union(i, j, i, j - 1);
                    }
                }
            }
        }

        return unionSet.getSize();
    }

    /**
     * 并查集
     *
     */
    class UnionSet {
        int size = 0;
        // 含义father[i] = k 意味着index为i的元素的节点的父亲的index是k
        int[] father;
        // 当前所属的节点总共有多少个节点
        int[] sizeMap;
        // 列
        int col;

        UnionSet(char[][] grid) {
            int row = grid.length;
            int col = grid[0].length;
            this.col = col;
            father = new int[row * col];
            sizeMap = new int[row * col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] == '1') {
                        size++;
                        int index = getIndex(i, j);
                        father[index] = index;
                    }
                }
            }
        }

        public int getSize() {
            return size;
        }

        // 计算下标
        public int getIndex(int row, int col) {
            return row * this.col + col;
        }

        public void union(int r1, int c1, int r2, int c2) {
            int index1 = getIndex(r1, c1);
            int index2 = getIndex(r2, c2);
            int father1 = findFather(index1);
            int father2 = findFather(index2);
            if (father1 != father2) {
                // Bug2: 应该比较和更新根节点 father1/father2 的 sizeMap，不是 index1/index2
                if (sizeMap[father1] < sizeMap[father2]) {
                    sizeMap[father2] += sizeMap[father1];
                    father[father1] = father2;
                } else {
                    sizeMap[father1] += sizeMap[father2];
                    father[father2] = father1;
                }
                size--;
            }
        }

        // 找到某个结点的父节点
        public int findFather(int index) {
            // Bug3: size 是岛屿数（会减小），应用 father.length 作为数组大小
            int[] help = new int[father.length];
            int helpIndex = 0;
            while (index != father[index]) {
                help[helpIndex++] = index;
                index = father[index];
            }
            while (helpIndex > 0) {
                father[help[--helpIndex]] = index;
            }

            return index;
        }
    }
}
