package leetcode75;

/**
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * <p>
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 */
public class Code437 {

    /**
     * 官解
     *
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum(TreeNode root, long targetSum) {
        if (root == null) {
            return 0;
        }
        int ans = 0;
        if (root.left == null && root.right == null) {
            return root.val == targetSum ? 1 : 0;
        }
        ans += process(root, targetSum);
        ans += pathSum(root.left, targetSum);
        ans += pathSum(root.right, targetSum);
        return ans;
    }

    public int process(TreeNode root, long targetSum) {
        if (root == null) {
            return 0;
        } else {
            int left = process(root.left, targetSum - root.val);
            int right = process(root.right, targetSum - root.val);
            return left + right + (root.val == targetSum ? 1 : 0);
        }
    }
}
