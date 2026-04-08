/**
 * 验证二叉搜索树
 * <p>
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 有效 二叉搜索树定义如下：
 * <p>
 * 节点的左子树只包含 严格小于 当前节点的数。
 * 节点的右子树只包含 严格大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 */
public class Code098 {
    public boolean isValidBST(TreeNode root) {
        return process(root) != null && process(root).isBST;
    }

    public Info process(TreeNode root) {
        if (root == null) {
            return null;
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        if (leftInfo == null && rightInfo == null) {
            // 两者都为空
            return new Info(root.val, root.val, true);
        } else if (leftInfo == null) {
            // right不为空
            boolean isBST = rightInfo.isBST && rightInfo.minVal > root.val;
            int maxValue = Math.max(root.val, rightInfo.maxVal);
            int minValue = Math.min(root.val, rightInfo.minVal);
            return new Info(maxValue, minValue, isBST);
        } else if (rightInfo == null) {
            // left不为空
            boolean isBST = leftInfo.isBST && leftInfo.maxVal < root.val;
            int maxValue = Math.max(root.val, leftInfo.maxVal);
            int minValue = Math.min(root.val, leftInfo.minVal);
            return new Info(maxValue, minValue, isBST);
        } else {
            // 两者都不为空
            boolean isBST = leftInfo.isBST && rightInfo.isBST && leftInfo.maxVal < root.val && rightInfo.minVal > root.val;
            int maxValue = Math.max(root.val, Math.max(leftInfo.maxVal, rightInfo.maxVal));
            int minValue = Math.min(root.val, Math.min(leftInfo.minVal, rightInfo.minVal));
            return new Info(maxValue, minValue, isBST);
        }
    }

    class Info {
        int maxVal;
        int minVal;
        boolean isBST;

        public Info(int maxVal, int minVal, boolean isBST) {
            this.maxVal = maxVal;
            this.minVal = minVal;
            this.isBST = isBST;
        }
    }

    /**
     * morris遍历
     *
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null) {
            if (cur.left == null) {
                if (pre != null && pre.val >= cur.val) {
                    return false;
                }
                pre = cur;
                cur = cur.right;
            } else {
                TreeNode left = cur.left;
                while (left.right != null && left.right != cur) {
                    left = left.right;
                }
                if (left.right == null) {
                    // 第一次来到这个节点
                    left.right = cur;
                    cur = cur.left;
                } else {
                    left.right = null;
                    if (pre != null && pre.val >= cur.val) {
                        return false;
                    }
                    pre = cur;
                    cur = cur.right;
                }
            }
        }
        return true;
    }

    public boolean isValidBST3(TreeNode root) {
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null) {
            TreeNode left = cur.left;
            if (left != null) {
                while (left.right != null && left.right != cur) {
                    left = left.right;
                }
                if (left.right == null) {
                    left.right = cur;
                    cur = cur.left;
                    continue;
                }else {
                    left.right = null;
                }
            }
            if (pre != null && pre.val >= cur.val) {
                return false;
            }
            pre = cur;
            cur = cur.right;
        }
        return true;
    }
}
