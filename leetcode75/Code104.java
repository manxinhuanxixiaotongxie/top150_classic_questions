package leetcode75;

public class Code104 {

    /**
     * 递归写法
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        return process(root);
    }


    /**
     * morris遍历
     *
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root) {
        if (root == null) return 0;

        int ans = 0;
        int depth = 0;

        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                cur = cur.right;
                depth++;
            } else {
                TreeNode mostLeftRightNode = cur.left;
                int curLevel = 1;
                while (mostLeftRightNode.right != null && mostLeftRightNode.right != cur) {
                    mostLeftRightNode = mostLeftRightNode.right;
                    curLevel++;
                }
                if (mostLeftRightNode.right == null) {
                    mostLeftRightNode.right = cur;
                    cur = cur.left;
                    depth++;
                } else {
                    if (mostLeftRightNode.left == null) {
                        ans = Math.max(ans, depth);
                    }
                    mostLeftRightNode.right = null;
                    cur = cur.right;
                    depth -= curLevel; // 回到上一个节点，深度减去当前层数
                }
            }
        }
        int finalRight = 1;
        cur = root;
        while (cur.right != null) {
            finalRight++;
            cur = cur.right;
        }
        if (cur.left == null) {
            ans = Math.max(ans, finalRight);
        }


        return ans;
    }

    public int process(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = process(root.left);
        int rightDepth = process(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
