import java.util.HashMap;
import java.util.Map;

public class Code138 {
    public Node copyRandomList(Node head) {
        // 随机链表的复制
        Map<Node, Node> map = new HashMap<Node, Node>();
        for (Node cur = head; cur != null; cur = cur.next) {
            map.put(cur, new Node(cur.val));
        }
        for (Node cur = head; cur != null; cur = cur.next) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
        }
        return map.get(head);
    }

    class Node {
        public int val;
        public Node next;
        public Node random;
        public Node(int _val) {
            val = _val;
        }
    }
}
