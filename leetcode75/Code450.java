package leetcode75;

/**
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 * <p>
 * 一般来说，删除节点可分为两个步骤：
 * <p>
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 */
public class Code450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        return delete(root, key);
    }

    public TreeNode delete(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val < key) {
            root.right = delete(root.right, key);
        } else if (root.val > key) {
            root.left = delete(root.left, key);
        } else {
            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.left == null) {
                root = root.right;
            } else if (root.right == null) {
                root = root.left;
            } else {
                // 两者都不为空
                // 使用右树的最左侧节点
                TreeNode minNode = root.right;
                while (minNode.left != null) {
                    minNode = minNode.left;
                }
                root.right = delete(root.right, minNode.val);
                minNode.left = root.left;
                minNode.right = root.right;
                root = minNode;
            }
        }
        return root;
    }

    /**
     * 这也是对的
     *
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode2(TreeNode root, int key) {
        return delete2(root, key);
    }


    public TreeNode delete2(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val < key) {
            root.right = delete2(root.right, key);
        } else if (root.val > key) {
            root.left = delete2(root.left, key);
        } else {
            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.left == null) {
                root = root.right;
            } else if (root.right == null) {
                root = root.left;
            } else {
                // 两者都不为空
                // 使用右树的最左侧节点
                TreeNode minNode = root.right;
                TreeNode pre = null;
                while (minNode.left != null) {
                    pre = minNode;
                    minNode = minNode.left;
                }
                if (pre != null) {
                    pre.left = minNode.right;
                    minNode.right = root.right;
                }
                minNode.left = root.left;
                root = minNode;
            }
        }
        return root;
    }
}
