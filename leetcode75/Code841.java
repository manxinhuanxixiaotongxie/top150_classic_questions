package leetcode75;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 有 n 个房间，房间按从 0 到 n - 1 编号。最初，除 0 号房间外的其余所有房间都被锁住。你的目标是进入所有的房间。然而，你不能在没有获得钥匙的时候进入锁住的房间。
 * <p>
 * 当你进入一个房间，你可能会在里面找到一套 不同的钥匙，每把钥匙上都有对应的房间号，即表示钥匙可以打开的房间。你可以拿上所有钥匙去解锁其他房间。
 * <p>
 * 给你一个数组 rooms 其中 rooms[i] 是你进入 i 号房间可以获得的钥匙集合。如果能进入 所有 房间返回 true，否则返回 false。
 */
public class Code841 {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int N = rooms.size();
        boolean[] visited = new boolean[N];
        Graph graph = new Graph(N);
        Node from = graph.graph.get(0);
        for (int i = 0; i < N; i++) {
            Node fromNode = graph.graph.get(i);
            List<Integer> list = rooms.get(i);
            for (Integer integer : list) {
                fromNode.edges.add(new Edge(fromNode, graph.graph.get(integer)));
            }
        }
        HashSet<Node> visitedSet = new HashSet<>();
        Queue<Node> queue = new java.util.LinkedList<>();
        queue.offer(from);
        visitedSet.add(from);
        visited[from.room] = true;
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            for (Edge edge : current.edges) {
                Node to = edge.to;
                if (!visitedSet.contains(to)) {
                    visitedSet.add(to);
                    visited[to.room] = true;
                    queue.offer(to);
                }
            }
        }
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }


    class Graph {
        Map<Integer, Node> graph;

        Graph(int n) {
            graph = new HashMap<>();
            for (int i = 0; i < n; i++) {
                graph.put(i, new Node(i));
            }
        }
    }

    class Node {
        int room;
        boolean visited;
        Set<Edge> edges;

        Node(int room) {
            this.room = room;
            this.visited = false;
            edges = new HashSet<>();
        }
    }

    class Edge {
        Node from;
        Node to;

        Edge(Node from, Node to) {
            this.from = from;
            this.to = to;
        }
    }

    /**
     * 图 拓扑排序
     * 更简易的写法
     *
     * @param rooms
     * @return
     */
    public boolean canVisitAllRooms2(List<List<Integer>> rooms) {
        if (rooms == null || rooms.isEmpty()) {
            return false;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        while (!queue.isEmpty()) {
            // 下一层
            int size = queue.size();
            // 下一层节点
            for (int i = 0; i < size; i++) {
                List<Integer> next = rooms.get(queue.poll());
                for (Integer integer : next) {
                    if (!visited.contains(integer)) {
                        visited.add(integer);
                        queue.offer(integer);
                    }
                }
            }
        }

        return visited.size() == rooms.size();
    }

    static void main(String[] args) {
        Code841 code841 = new Code841();
        List<List<Integer>> rooms = List.of(
                List.of(1),
                List.of(2),
                List.of(3),
                List.of()
        );
        boolean result = code841.canVisitAllRooms(rooms);
        System.out.println(result);
        boolean result2 = code841.canVisitAllRooms2(rooms);
        System.out.println(result2);
    }
}
