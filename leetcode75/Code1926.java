package leetcode75;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给你一个 m x n 的迷宫矩阵 maze （下标从 0 开始），矩阵中有空格子（用 '.' 表示）和墙（用 '+' 表示）。同时给你迷宫的入口 entrance ，
 * 用 entrance = [entrancerow, entrancecol] 表示你一开始所在格子的行和列。
 * <p>
 * 每一步操作，你可以往 上，下，左 或者 右 移动一个格子。你不能进入墙所在的格子，你也不能离开迷宫。你的目标是找到离 entrance 最近 的出口。出口 的含义是 maze 边界 上的 空格子。entrance 格子 不算 出口。
 * <p>
 * 请你返回从 entrance 到最近出口的最短路径的 步数 ，如果不存在这样的路径，请你返回 -1 。
 */
public class Code1926 {
    public int nearestExit(char[][] maze, int[] entrance) {
        // 宽度优先遍历
        int row = maze.length;
        int col = maze[0].length;
        boolean[][] visited = new boolean[row][col];
        visited[entrance[0]][entrance[1]] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{entrance[0], entrance[1]});
        // 定义上下左右四个方向的移动
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};
        int ans = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            ans++;
            for (int i = 0; i < size; i++) {
                int[] node = queue.poll();
                if ((node[0] == row - 1 || node[1] == col - 1 || node[0] == 0 || node[1] == 0) && !(node[0] == entrance[0] && node[1] == entrance[1])) {
                    return ans;
                }
                for (int k = 0; k < 4; k++) {
                    int x = node[0] + dx[k];
                    int y = node[1] + dy[k];
                    if (x < 0 || y < 0 || x >= row || y >= col || maze[x][y] == '+' || visited[x][y]) {
                        continue; // 越界
                    }
                    visited[x][y] = true; // 标记为已访问
                    queue.add(new int[]{x, y}); // 将新位置加入队列
                }


            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Code1926 code1926 = new Code1926();
        /**
         * [["+",".","+","+","+","+","+"],["+",".","+",".",".",".","+"],["+",".","+",".","+",".","+"],["+",".",".",".","+",".","+"],["+","+","+","+","+","+","."]]
         */
        char[][] maze = {
                {'+', '.', '+', '+', '+', '+', '+'},
                {'+', '.', '+', '.', '.', '.', '+'},
                {'+', '.', '+', '.', '+', '.', '+'},
                {'+', '.', '.', '.', '+', '.', '+'},
                {'+', '+', '+', '+', '+', '+', '.'}
        };
        int[] entrance = {0, 1};
        int result = code1926.nearestExit(maze, entrance);
        System.out.println(result); // 输出 3
    }
}
