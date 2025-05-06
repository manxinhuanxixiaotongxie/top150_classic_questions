import java.util.LinkedList;

public class Code117_Connect {
    public Node connect(Node root) {

        // 宽度优先遍历
        LinkedList<Node> queue = new LinkedList<Node>();

        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            // 构造一个影子节点
            Node shadow = new Node(0);

            for (int i = 0; i < size; i++) {

                Node poll = queue.poll();

                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
                shadow.next = poll;
                shadow = poll;
            }

        }

        return root;
    }

    class Node {
        Node left;
        Node right;
        Node next;
        int val;

        Node(int val) {
            this.val = val;
        }

        Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        Code117_Connect code117_connect = new Code117_Connect();
        Node node = code117_connect.new Node(1);
        Node node1 = code117_connect.new Node(2);
        Node node2 = code117_connect.new Node(3);
        Node node3 = code117_connect.new Node(4);
        Node node4 = code117_connect.new Node(5);
        Node node5 = code117_connect.new Node(7);
        node.left = node1;
        node.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.right = node5;
        Node connect = code117_connect.connect(node);
        System.out.println(connect);
    }
}
