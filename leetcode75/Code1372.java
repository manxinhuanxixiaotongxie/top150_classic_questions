package leetcode75;

/**
 * 给你一棵以 root 为根的二叉树，二叉树中的交错路径定义如下：
 * <p>
 * 选择二叉树中 任意 节点和一个方向（左或者右）。
 * 如果前进方向为右，那么移动到当前节点的的右子节点，否则移动到它的左子节点。
 * 改变前进方向：左变右或者右变左。
 * 重复第二步和第三步，直到你在树中无法继续移动。
 * 交错路径的长度定义为：访问过的节点数目 - 1（单个节点的路径长度为 0 ）。
 * <p>
 * 请你返回给定树中最长 交错路径 的长度。
 */
public class Code1372 {
    int ans = 0;

    public int longestZigZag(TreeNode root) {
        if (root == null || root.left == null && root.right == null) {
            return 0; // 如果树为空或只有一个节点，返回0
        }
        process(root, true, 0);
        process(root, false, 0);
        return ans;
    }

    public void process(TreeNode root, boolean way, int len) {
        ans = Math.max(ans, len);
        if (way) {
            // 之前是向左
            if (root.right != null) {
                process(root.right, false, len + 1);
            }
            if (root.left != null) {
                process(root.left, true, 1);
            }
        } else {
            if (root.left != null) {
                process(root.left, true, len + 1);
            }
            if (root.right != null) {
                process(root.right, false, 1);
            }
        }
    }
}
