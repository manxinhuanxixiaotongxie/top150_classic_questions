import java.util.LinkedList;
import java.util.List;

public class Code199 {
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return new LinkedList<>();
        List<Integer> ans = new LinkedList<>();
        // 宽度优先遍历
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addFirst(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ans.add(queue.peekLast().val);
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }
        }
        return ans;
    }
}
