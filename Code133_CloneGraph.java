import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 提示：
 * <p>
 * 这张图中的节点数在 [0, 100] 之间。
 * 1 <= Node.val <= 100
 * 每个节点值 Node.val 都是唯一的，
 * 图中没有重复的边，也没有自环。
 * 图是连通图，你可以从给定节点访问到所有节点。
 */
public class Code133_CloneGraph {

    public Node cloneGraph(Node node) {
        if (node == null) return node;
//        LinkedList<Node> queue = new LinkedList<>();
//        Set<Node> visited = new HashSet<>();
//        queue.add(node);
//        visited.add(node);
//        Node newHead = new Node(node.val);
//        while (!queue.isEmpty()) {
//            Node poll = queue.poll();
//            for (Node neighbor : poll.neighbors) {
//                if (!visited.contains(neighbor)) {
//                    Node newNode = new Node(neighbor.val);
//                    queue.add(neighbor);
//                    visited.add(neighbor);
//                    poll.neighbors.add(newNode);
//                }
//
//            }
//        }

        Map<Node, Node> map = new HashMap<>();
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(node);
        map.put(node, new Node(node.val));
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            for (Node neighbor : poll.neighbors) {
                if (!map.containsKey(neighbor)) {
                    queue.add(neighbor);
                    map.put(neighbor, new Node(neighbor.val));
                }
                map.get(poll).neighbors.add(map.get(neighbor));
            }
        }
        return map.get(node);
    }

    public Node cloneGraph2(Node node) {
        if (node == null) {
            return node;
        }

        HashMap<Node, Node> visited = new HashMap();

        // 将题目给定的节点添加到队列
        LinkedList<Node> queue = new LinkedList<Node>();
        queue.add(node);
        // 克隆第一个节点并存储到哈希表中
        visited.put(node, new Node(node.val, new ArrayList()));

        // 广度优先搜索
        while (!queue.isEmpty()) {
            // 取出队列的头节点
            Node n = queue.remove();
            // 遍历该节点的邻居
            for (Node neighbor : n.neighbors) {
                if (!visited.containsKey(neighbor)) {
                    // 如果没有被访问过，就克隆并存储在哈希表中
                    visited.put(neighbor, new Node(neighbor.val, new ArrayList()));
                    // 将邻居节点加入队列中
                    queue.add(neighbor);
                }
                // 更新当前节点的邻居列表
                visited.get(n).neighbors.add(visited.get(neighbor));
            }
        }

        return visited.get(node);
    }

}

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
