public class Code236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }
        return process(root, p, q).ans;
    }

    public Info process(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        Info leftInfo = process(root.left, p, q);
        Info rightInfo = process(root.right, p, q);
        // 整合左右树信息 构建当前节点信息
        if (leftInfo == null && rightInfo == null) {
            boolean findP = root == p;
            boolean findQ = root == q;
            TreeNode ans = null;
            if (findP && findQ) {
                ans = root;
            }
            return new Info(ans, findP, findQ);
        }else if (leftInfo == null) {
            // 右树不为空
            boolean findP = root == p || rightInfo.findP;
            boolean findQ = root == q  || rightInfo.findQ;
            TreeNode ans = null;
            if (rightInfo.ans != null) {
                ans = rightInfo.ans;
            }else if (findP && findQ) {
                ans = root;
            }
            return new Info(ans, findP, findQ);
        }else if (rightInfo == null) {
            // 左树不为空
            boolean findP = root == p  || leftInfo.findP;
            boolean findQ = root == q || leftInfo.findQ;
            TreeNode ans = null;
            if (leftInfo.ans != null) {
                ans = leftInfo.ans;
            } else if (findP && findQ) {
                ans = root;
            }
            return new Info(ans, findP, findQ);
        } else {
            // 左右树都不为空
            boolean findP = root == p  || leftInfo.findP || rightInfo.findP;
            boolean findQ = root == q || leftInfo.findQ || rightInfo.findQ;
            TreeNode ans = null;
            if (leftInfo.ans != null) {
                ans = leftInfo.ans;
            } else if (rightInfo.ans != null) {
                ans = rightInfo.ans;
            } else if (findP && findQ) {
                ans = root;
            }
            return new Info(ans, findP, findQ);
        }
    }

    class Info {
        TreeNode ans;
        boolean findP;
        boolean findQ;
        Info(TreeNode ans, boolean findP, boolean findQ) {
            this.ans = ans;
            this.findP = findP;
            this.findQ = findQ;
        }
    }
}
