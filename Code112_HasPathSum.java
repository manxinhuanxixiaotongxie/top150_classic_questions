public class Code112_HasPathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        return process(root, targetSum);
    }

    public boolean process(TreeNode root, int rest) {
        if (root.left == null && root.right == null) {
            return rest == root.val;
        }
        boolean left = root.left != null && process(root.left, rest - root.val);
        boolean right = root.right != null && process(root.right, rest - root.val);
        return left || right;
    }
}
