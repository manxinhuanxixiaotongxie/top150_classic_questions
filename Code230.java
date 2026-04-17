/**
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 小的元素（k 从 1 开始计数）。
 *
 */
public class Code230 {
    /***
     * 第一个思路 二叉搜索树中序遍历的k-1个数
     *
     * @param root
     * @param k
     * @return
     */
    private int k = 0;
    private int ans = -1;

    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        process(root);
        return ans;
    }

    public void process(TreeNode root) {
        if (root == null) {
            return;
        }
        process(root.left);
        if (--k == 0) {
            ans = root.val;
        }
        process(root.right);
    }

    // 中序遍历 改写morris遍历
    public int kthSmallest2(TreeNode root, int k) {
        TreeNode cur = root;
        while (cur != null) {
            TreeNode node = cur.left;
            if (node != null) {
                // 有左树
                while (node.right != null && node.right != cur) {
                    node = node.right;
                }
                if (node.right == null) {
                    // 该节点是第一次来到
                    node.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    // 第二次来到 是中序遍历
                    node.right = null;
                }
            }
            if (--k == 0) {
                return cur.val;
            }
            cur = cur.right;
        }

        return -1;
    }

}
