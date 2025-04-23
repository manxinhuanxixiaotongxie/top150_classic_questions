import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Code103_ZigzagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // 如果为true代表从左到右
        // 如果为false代表从右到左
        boolean flag = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> cur = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                cur.add(node.val);
            }
            if (!flag) {
                // 倒装list
                int l = 0;
                int r = cur.size() - 1;
                while (l < r) {
                    swap(cur, l++, r--);
                }
            }
            flag = !flag;
            res.add(cur);
        }
        return res;
    }

    public void swap(List<Integer> cur, int i, int j) {
        int temp = cur.get(i);
        cur.set(i, cur.get(j));
        cur.set(j, temp);
    }
}
