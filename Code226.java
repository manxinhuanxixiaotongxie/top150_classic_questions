public class Code226 {
    public TreeNode invertTree(TreeNode root) {
        return process(root);
    }

    public TreeNode process(TreeNode root) {
        if (root == null) return root;
        if (root.left == null && root.right == null) return root;
        // 翻转左树
        TreeNode left = process(root.left);
        // 翻转右树
        root.left = process(root.right);
        root.right = left;
        return root;
    }
}
