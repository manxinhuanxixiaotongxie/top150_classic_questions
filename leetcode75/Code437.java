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

    /**
     * 这个函数解决的问题是：整棵树中任意位置开始的路径，有多少条路径和等于targetSum
     * 1.以当前 root 为路径起点，调用 process(root, targetSum) 统计
     * 2.以左子树中的节点为起点，递归调用 pathSum(root.left, targetSum)
     * 3.以右子树中的节点为起点，递归调用 pathSum(root.right, targetSum)
     *
     * 所以本质上是一个枚举起点 + 从起点往下搜索的两层结构，必须拆成两个递归。
     *
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum2(TreeNode root, long targetSum) {
        if (root == null) {
            return 0;
        }
        int ans = 0;
        ans += process2(root, targetSum);
        ans += pathSum2(root.left, targetSum);
        ans += pathSum2(root.right, targetSum);

        return ans;
    }

    /**
     * 这个递归解决的问题是：必须经过当前节点root的路径中 有多少条路径和等于targetSum
     * 这个递归中 路径是固定从root开始向下走的，target随着节点不断变化
     * @param root
     * @param targetSum
     * @return
     */
    public int process2(TreeNode root, long targetSum) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        if (targetSum == root.val) {
            count++;
        }

        count += process2(root.left, targetSum - root.val);
        count += process2(root.right, targetSum - root.val);
        return count;
    }
}
