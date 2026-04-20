public class Code530_GetMinimumDifference {
    int pre;
    int ans;

    /**
     *
     * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
     *
     * 差值是一个正数，其数值等于两值之差的绝对值。
     *
     *
     * 中序遍历
     *
     * @param root
     * @return
     */
    public int getMinimumDifference(TreeNode root) {
        pre = -1;
        ans = Integer.MAX_VALUE;
        process(root);
        return ans;
    }

    /**
     * 中序优先遍历进行比较
     *
     * @param root
     */
    public void process(TreeNode root) {
        if (root == null) return;
        process(root.left);
        if (pre == -1) pre = root.val;
        else {
            ans = Math.min(ans, Math.abs(root.val - pre));
            pre = root.val;
        }
        process(root.right);
    }

    /**
     * 使用morris遍历改写
     *
     * @param root
     * @return
     */
    public int getMinimumDifference2(TreeNode root) {
        if (root == null) return 0;
        int pre = -1;
        int ans = Integer.MAX_VALUE;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                if (pre == -1) pre = cur.val;
                else {
                    ans = Math.min(ans, Math.abs(cur.val - pre));
                    pre = cur.val;
                }
                cur = cur.right;
            } else {
                TreeNode mostRightNode = cur.left;
                while (mostRightNode.right != null && mostRightNode.right != cur) {
                    mostRightNode = mostRightNode.right;
                }
                if (mostRightNode.right == null) {
                    mostRightNode.right = cur;
                    cur = cur.left;
                } else {
                    if (pre == -1) pre = cur.val;
                    else {
                        ans = Math.min(ans, Math.abs(cur.val - pre));
                        pre = cur.val;
                    }
                    cur = cur.right;
                    mostRightNode.right = null;
                }
            }
        }
        return ans;
    }

}
