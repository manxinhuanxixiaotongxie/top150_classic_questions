package leetcode75;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
 * <p>
 * 值 0 代表空单元格；
 * 值 1 代表新鲜橘子；
 * 值 2 代表腐烂的橘子。
 * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
 * <p>
 * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
 */
public class Code994 {
    public int orangesRotting(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue<int[]> queue = new LinkedList<int[]>();
        // 定义上下左右
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true; // 标记腐烂的橘子已访问
                }
                if (grid[i][j] == 0) {
                    visited[i][j] = true;
                }
            }
        }
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            ans++;
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for (int k = 0; k < 4; k++) {
                    int x = cur[0] + dx[k];
                    int y = cur[1] + dy[k];
                    if (x < 0 || x >= row || y < 0 || y >= col || visited[x][y] || grid[x][y] != 1) {
                        continue;
                    }
                    visited[x][y] = true;
                    if (grid[x][y] == 1) {
                        queue.offer(new int[]{x, y});
                        grid[x][y] = 2;
                    }
                }
            }
        }
        // 检查是否还有新鲜橘子
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    return -1; // 如果还有新鲜橘子，返回 -1
                }
            }
        }
        return ans == 0 ? 0 : ans - 1; // 如果没有新鲜橘子，返回 ans - 1
    }

    public static void main(String[] args) {
        Code994 solution = new Code994();
        int[][] grid = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };
        int result = solution.orangesRotting(grid);
        System.out.println(result); // 输出结果
    }
}
