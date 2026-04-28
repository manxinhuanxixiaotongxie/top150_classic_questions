package leetcode75;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Code236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 记录父节点
        Map<TreeNode, TreeNode> map = new HashMap<>();
        map.put(root, null);
        process(root, map);
        // 找到节点的父节点之后
        // 从p开始查找
        LinkedList<TreeNode> list = new LinkedList<>();
        TreeNode father = p;
        while (father != null) {
            list.addFirst(father);
            father = map.get(father);
        }
        // 得到了p节点的所有父节点
        TreeNode node = list.pollLast();
        while (node != null) {
            // 从p节点向上查找
            father = q;
            while (father != null) {
                if (father == node) {
                    return node; // 找到q的父节点在p的父节点中
                }
                father = map.get(father);
            }
            node = list.pollLast();
        }
        return root;
    }

    public void process(TreeNode root, Map<TreeNode, TreeNode> map) {
        if (root != null) {
            if (root.left != null) {
                map.put(root.left, root);
                process(root.left, map);
            }
            if (root.right != null) {
                map.put(root.right, root);
                process(root.right, map);
            }
        }
    }

    /**
     * 万能公式
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
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

    public static void main(String[] args) {
        // -1,0,3,-2,4,null,null,8
        Code236 code236 = new Code236();
        TreeNode root = new TreeNode(-1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(-2);
        root.left.right = new TreeNode(4);
        root.left.left.right = new TreeNode(8);
        // p 8
        TreeNode p = root.left.left.right;
        // q 0
        TreeNode q = root.left;
        TreeNode ans = code236.lowestCommonAncestor(root, p, q);
        System.out.println(ans.val); // 输出应该是0
    }
}
