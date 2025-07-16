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
