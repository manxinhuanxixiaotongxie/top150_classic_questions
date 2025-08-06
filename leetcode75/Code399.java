package leetcode75;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


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
public class Code399 {

    /**
     * 使用图的深度优先遍历实现
     *
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, List<Pair>> graph = new HashMap<>();
        // 构建图
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            for (String s : equation) {
                if (!graph.containsKey(s)) {
                    graph.put(s, new ArrayList<>());
                }
            }
        }

        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String from = equation.get(0);
            String to = equation.get(1);
            graph.get(from).add(new Pair(to, values[i]));
            graph.get(to).add(new Pair(from, 1 / values[i]));
        }
        // 图的深度优先遍历
        Map<String, Double> map = new HashMap<>();
        for (String s : graph.keySet()) {
            Set<String> set = new HashSet<>();
            set.add(s);
            map.put(s + "_" + s, 1.0); // 自己除以自己等于1
            process(graph, s, s, 1.0, set, map);
        }
        double[] ans = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String from = query.get(0);
            String to = query.get(1);
            if (map.containsKey(from + "_" + to)) {
                ans[i] = map.get(from + "_" + to);
            } else {
                ans[i] = -1.0;
            }
        }
        return ans;
    }

    public void process(Map<String, List<Pair>> graph, String from, String node, double val, Set<String> set, Map<String, Double> map) {
        set.add(node);
        for (Pair pair : graph.get(node)) {
            if (!set.contains(pair.node)) {
                set.add(pair.node);
                map.put(from + "_" + pair.node, val * pair.cost);
                process(graph, from, pair.node, val * pair.cost, set, map);
            }
        }
    }

    class Pair {
        String node;
        Double cost;

        Pair(String node, Double cost) {
            this.node = node;
            this.cost = cost;
        }
    }

}
