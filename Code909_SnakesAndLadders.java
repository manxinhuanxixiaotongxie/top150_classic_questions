import java.util.LinkedList;
import java.util.Queue;

public class Code909_SnakesAndLadders {
    /**
     * 使用图的宽度优先遍历
     *
     * @param board
     * @return
     */
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        boolean[] vis = new boolean[n * n + 1];
        vis[1] = true; // 题目保证起点没有蛇梯，不写也可以
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        for (int step = 0; !queue.isEmpty(); step++) {
            int size = queue.size();
            // 宽度优先遍历
            for (int i = 0; i < size; i++) {
                int x = queue.poll();
                if (x == n * n) { // 终点
                    return step;
                }
                for (int y = x + 1; y <= Math.min(x + 6, n * n); y++) {
                    // 计算下标
                    int r = (y - 1) / n;
                    int c = (y - 1) % n;
                    if (r % 2 > 0) {
                        c = n - 1 - c; // 奇数行从右到左
                    }
                    int nxt = board[n - 1 - r][c];
                    if (nxt < 0) {
                        nxt = y;
                    }
                    if (!vis[nxt]) {
                        vis[nxt] = true; // 有环的情况下，避免死循环
                        queue.add(nxt);
                    }
                }
            }
        }
        return -1; // 无法到达终点
    }
}
