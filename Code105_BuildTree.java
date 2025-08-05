import java.util.HashMap;
import java.util.Map;

/**
 * 从前序与中序遍历的构造二叉树
 */
public class Code105_BuildTree {
    // 前序遍历的顺序是  头左右
    // 中序遍历的顺序是  左头右
    // 必须要保证整个树是没有重复节点的
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len1 = preorder.length;
        int len2 = inorder.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return process2(preorder, 0, len1 - 1, inorder, 0, len2 - 1, map);
    }


    public TreeNode process2(int[] pre, int l1, int r1, int[] inorder, int l2, int r2, Map<Integer, Integer> map) {
        if (l1 > r1) {
            return null;
        }
        if (l1 == r1) {
            return new TreeNode(pre[l1]);
        }
        // 在右侧中找打l1所在的位置
        int index = map.get(pre[l1]);
        // l2 到index就是左树的范围
        TreeNode cur = new TreeNode(pre[l1]);
        // 此时左树的范围就是 l1+1 到 l1+index-l2
        cur.left = process2(pre, l1 + 1, l1 + index - l2, inorder, l2, index - 1, map);
        cur.right = process2(pre, l1 + index - l2 + 1, r1, inorder, index + 1, r2, map);
        return cur;
    }

}
