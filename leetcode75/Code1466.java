package leetcode75;

import java.util.ArrayList;
import java.util.List;

/**
 * n 座城市，从 0 到 n-1 编号，其间共有 n-1 条路线。因此，要想在两座不同城市之间旅行只有唯一一条路线可供选择（路线网形成一颗树）。
 * ，交通运输部决定重新规划路线，以改变交通拥堵的状况。
 * <p>
 * 路线用 connections 表示，其中 connections[i] = [a, b] 表示从城市 a 到 b 的一条有向路线。
 * <p>
 * 今年，城市 0 将会举办一场大型比赛，很多游客都想前往城市 0 。
 * <p>
 * 请你帮助重新规划路线方向，使每个城市都可以访问城市 0 。返回需要变更方向的最小路线数。
 * <p>
 * 题目数据 保证 每个城市在重新规划路线方向后都能到达城市 0 。
 */
public class Code1466 {
    /**
     * 图的深度优先遍历
     * 参考leetcode评论区的解法
     *
     * @param n
     * @param connections
     * @return
     */

    private int ans = 0;

    public int minReorder(int n, int[][] connections) {
        // 有向无环图 n个节点有n-1个边
        ans = 0;
        boolean[] visited = new boolean[n];
        List<List<Integer>> graph = buildGraph(n, connections);
        process(graph, 0, visited);
        return ans;
    }

    public void process(List<List<Integer>> graph, int from, boolean[] visited) {
        visited[from] = true;
        for (Integer next : graph.get(from)) {
            if (!visited[Math.abs(next)]) {
                if (next > 0) {
                    // 大于0意味着需要构建一个从to 到from的边
                    ans++;
                }
                process(graph, Math.abs(next), visited);
            }
        }
    }

    /**
     * 构件图
     * 采用邻接表法
     *
     * @return
     */
    public List<List<Integer>> buildGraph(int n, int[][] connections) {
        if (connections == null || connections.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < connections.length; i++) {
            int from = connections[i][0];
            int to = connections[i][1];
            // 构建从from到to的有向边
            graph.get(from).add(to);
            // 方便计算
            graph.get(to).add(-from);
        }
        return graph;
    }
}
