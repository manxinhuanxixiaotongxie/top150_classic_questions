import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。
 * 每个 Ai 或 Bi 是一个表示单个变量的字符串。
 * <p>
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
 * <p>
 * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。
 * <p>
 * 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
 * <p>
 * 注意：未在等式列表中出现的变量是未定义的，因此无法确定它们的答案。
 */
public class Code399_CalcEquation {
    /**
     * 官解
     * <p>
     * 将整个问题建模成一张图：给定图中一些点以及某些边的权值 试对任意两点求出其路径长
     * <p>
     * 1.遍历equations数组 找到其中所有不同的字符串  通过hash表将每个不同的字符串映射成整数
     * <p>
     * 2.完成图的构建 对于每一个查询就可以从起点出发 通过广度优先遍历的方式 不断更新起点与当前点之间的路径长度直到搜索到重点为止
     *
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        // 总共有多少个点
        int nodes = 0;
        Map<String, Integer> nodeIndexMap = new HashMap<>();

        int n = equations.size();
        for (int i = 0; i < n; i++) {
            if (!nodeIndexMap.containsKey(equations.get(i).get(0))) {
                nodeIndexMap.put(equations.get(i).get(0), nodes++);
            }
            if (!nodeIndexMap.containsKey(equations.get(i).get(1))) {
                nodeIndexMap.put(equations.get(i).get(1), nodes++);
            }
        }

        // 对于每个点，存储其直接连接到的所有点及对应的权值
        // 每个点对应的边
        List<Pair>[] edges = new List[nodes];
        for (int i = 0; i < nodes; i++) {
            edges[i] = new ArrayList<Pair>();
        }

        for (int i = 0; i < n; i++) {
            // from是起点
            // to是终点
            int from = nodeIndexMap.get(equations.get(i).get(0)), to = nodeIndexMap.get(equations.get(i).get(1));
            // 起点对应的边
            edges[from].add(new Pair(to, values[i]));
            // 终点对应的边
            edges[to].add(new Pair(from, 1.0 / values[i]));
        }

        int queriesCount = queries.size();
        double[] ret = new double[queriesCount];
        for (int i = 0; i < queriesCount; i++) {
            // 需要查询的两个点
            List<String> query = queries.get(i);
            double result = -1.0;
            if (nodeIndexMap.containsKey(query.get(0)) && nodeIndexMap.containsKey(query.get(1))) {
                int from = nodeIndexMap.get(query.get(0)), to = nodeIndexMap.get(query.get(1));
                if (from == to) {
                    result = 1.0;
                } else {
                    Queue<Integer> points = new LinkedList<>();
                    points.offer(from);
                    double[] distance = new double[nodes];
                    Arrays.fill(distance, -1.0);
                    distance[from] = 1.0;

                    while (!points.isEmpty() && distance[to] < 0) {
                        int poll = points.poll();
                        // poll的所有的边
                        for (Pair pair : edges[poll]) {
                            int toIndex = pair.index;
                            // poll到toIndex的距离
                            double toDistance = pair.value;
                            // 还没计算过
                            if (distance[toIndex] < 0) {
                                // poll到toIndex的距离 沿途的distance相乘
                                distance[toIndex] = distance[poll] * toDistance;
                                points.offer(toIndex);
                            }
                        }

                    }
                    result = distance[to];
                }
            }
            ret[i] = result;
        }
        return ret;
    }
}

class Pair {
    int index;
    double value;

    Pair(int index, double value) {
        this.index = index;
        this.value = value;
    }

}
