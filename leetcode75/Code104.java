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


    /**
     * morris遍历
     *
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root) {
        if (root == null) return 0;

        int ans = 0;
        int depth = 0;

        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                cur = cur.right;
                depth++;
            } else {
                TreeNode mostLeftRightNode = cur.left;
                int curLevel = 1;
                while (mostLeftRightNode.right != null && mostLeftRightNode.right != cur) {
                    mostLeftRightNode = mostLeftRightNode.right;
                    curLevel++;
                }
                if (mostLeftRightNode.right == null) {
                    mostLeftRightNode.right = cur;
                    cur = cur.left;
                    depth++;
                } else {
                    if (mostLeftRightNode.left == null) {
                        ans = Math.max(ans, depth);
                    }
                    mostLeftRightNode.right = null;
                    cur = cur.right;
                    depth -= curLevel; // 回到上一个节点，深度减去当前层数
                }
            }
        }
        int finalRight = 1;
        cur = root;
        while (cur.right != null) {
            finalRight++;
            cur = cur.right;
        }
        if (cur.left == null) {
            ans = Math.max(ans, finalRight);
        }


        return ans;
    }

    public int maxDepth3(TreeNode root) {
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
                    // 注释掉也是对的
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

    public int process(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = process(root.left);
        int rightDepth = process(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
