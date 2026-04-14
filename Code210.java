import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，
 * 表示在选修课程 ai 前 必须 先选修 bi 。
 *
 * 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
 * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
 *
 */
public class Code210 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Graph graph = new Graph();
        int[] ans = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            ans[i] = i;
        }
        if (prerequisites == null || prerequisites.length == 0) {
            return ans;
        }

        Map<Integer, GraphNode> map = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            int from = prerequisites[i][1];
            int to = prerequisites[i][0];
            if (!map.containsKey(from)) {
                map.put(from, new GraphNode(from));
                graph.nodes.add(map.get(from));
            }
            if (!map.containsKey(to)) {
                map.put(to, new GraphNode(to));
                graph.nodes.add(map.get(to));
            }
            GraphNode fromNode = map.get(from);
            GraphNode toNode = map.get(to);
            fromNode.nexts.add(toNode);
            toNode.in++;
        }

        Queue<GraphNode> queue = new LinkedList<>();
        int index = 0;
        for (int i = 0; i < numCourses; i++) {
            if (!map.containsKey(i)) {
                ans[index++] = i;
            } else if (map.get(i).in == 0) {
                queue.add(map.get(i));
            }
        }
        Set<GraphNode> set = new HashSet<>();
        while (!queue.isEmpty()) {
            GraphNode node = queue.poll();
            set.add(node);
            ans[index++] = (int) node.val;
            for (int i = 0; i < node.nexts.size(); i++) {
                GraphNode to = (GraphNode) node.nexts.get(i);
                to.in--;
                if (to.in == 0 && !set.contains(to)) {
                    queue.add(to);
                }
            }
        }

        if (set.size() == graph.nodes.size()) {
            return ans;
        }

        return new int[0];
    }

    class Graph {
        List<GraphNode> nodes;

        Graph() {
            this.nodes = new ArrayList<>();
        }
    }

    class GraphNode<Integer> {
        Integer val;
        GraphNode from;
        List<GraphNode> nexts;
        int in;

        GraphNode(Integer val) {
            this.nexts = new ArrayList<GraphNode>();
            this.val = val;
            this.in = 0;
        }

        // 重写equals方法
        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            if (obj instanceof GraphNode) {
                GraphNode node = (GraphNode) obj;
                return this.val.equals(node.val);
            }
            return false;
        }
    }
}
