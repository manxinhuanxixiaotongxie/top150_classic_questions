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

    public int process(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = process(root.left);
        int rightDepth = process(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
