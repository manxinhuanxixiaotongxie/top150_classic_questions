public class Code104 {

    public int maxDepth(TreeNode root) {
        return process(root);
    }

    public int process(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = process(root.left);
        int right = process(root.right);
        return Math.max(left, right) + 1;
    }

    /**
     * 使用morris遍历实现
     *
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root) {
        TreeNode cur = root;
        int ans = 0;
        int curHeight = 0;
        while (cur != null) {
            TreeNode left = cur.left;
            if (left != null) {
                int curLevelHeight = 1;
                while (left.right != null && left.right != cur) {
                    curLevelHeight++;
                    left = left.right;
                }
                if (left.right == null) {
                    left.right = cur;
                    curHeight++;
                    cur = cur.left;
                    continue;
                } else {
                    left.right = null;
                    ans = Math.max(curHeight, ans);
                    curHeight -= (curLevelHeight + 1);
                }
            }
            curHeight++;
            ans = Math.max(curHeight, ans);
            cur = cur.right;
        }
        return ans;
    }
}
