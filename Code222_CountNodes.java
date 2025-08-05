import java.util.LinkedList;

public class Code222_CountNodes {
    /**
     * 宽度优先遍历
     *
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            ans += size;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return ans;
    }

    /**
     * 深度优先遍历
     *
     * @param root
     * @return
     */
    int ans = 0;

    public int countNodes2(TreeNode root) {
        ans = 0;
        process(root);
        return ans;
    }

    public void process(TreeNode root) {
        if (root == null) return;
        ans++;
        process(root.left);
        process(root.right);
    }

    /**
     * 时间复杂度O(N)
     * 空间复杂度O（1）
     * morris遍历
     *
     * @param root
     * @return
     */
    public int countNodes3(TreeNode root) {
        if (root == null) return 0;
        /**
         * morris遍历的流程
         * 1.如果节点没有左树 当前节点来到右树
         * 2.如果当前节点有左树 那么找到左树的最右侧节点 将左树的最右侧节点的右树指向当前节点 并且当前节点来到左树
         */
        TreeNode cur = root;
        int ans = 0;
        // 使用morris遍历 改写二叉树的先序优先遍历
        while (cur != null) {
            if (cur.left == null) {
                cur = cur.right;
                ans++;
            } else {
                // 有左树
                TreeNode mostRightNode = cur.left;
                while (mostRightNode.right != null && mostRightNode.right != cur) {
                    mostRightNode = mostRightNode.right;
                }
                if (mostRightNode.right == null) {
                    mostRightNode.right = cur;
                    cur = cur.left;
                    ans++;
                } else {
                    // 此时 cur已经是第二次来到当前节点了
                    cur = cur.right;
                    mostRightNode.right = null;
                }
            }
        }
        return ans;
    }

    /**
     * 使用morris遍历改写中序优先遍历
     *
     * @param root
     * @return
     */
    public int countNodes4(TreeNode root) {
        if (root == null) return 0;
        /**
         * morris遍历的流程
         * 1.如果节点没有左树 当前节点来到右树
         * 2.如果当前节点有左树 那么找到左树的最右侧节点 将左树的最右侧节点的右树指向当前节点 并且当前节点来到左树
         */
        TreeNode cur = root;
        int ans = 0;
        // 使用morris遍历 改写二叉树的先序优先遍历
        while (cur != null) {
            if (cur.left == null) {
                cur = cur.right;
                ans++;
            } else {
                // 有左树
                TreeNode mostRightNode = cur.left;
                while (mostRightNode.right != null && mostRightNode.right != cur) {
                    mostRightNode = mostRightNode.right;
                }
                if (mostRightNode.right == null) {
                    mostRightNode.right = cur;
                    cur = cur.left;
                } else {
                    // 此时 cur已经是第二次来到当前节点了
                    cur = cur.right;
                    mostRightNode.right = null;
                    ans++;
                }
            }
        }
        return ans;
    }
}
