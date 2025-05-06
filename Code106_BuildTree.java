import java.util.HashMap;
import java.util.Map;

/**
 * 从中序 与后序遍历构造二叉树
 */
public class Code106_BuildTree {
    /**
     * 使用中序与后序遍历构造二叉树
     * 实现方式与105的使用前序、中序构造二叉树相似
     * <p>
     * <p>
     * 中序的遍历方式是左头右
     * 后序的遍历方式是左右头
     *
     * @param inOrder
     * @param postOrder
     * @return
     */
    public TreeNode buildTree(int[] inOrder, int[] postOrder) {
        int len1 = inOrder.length;
        int len2 = postOrder.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inOrder.length; i++) {
            map.put(inOrder[i], i);
        }

        return process2(inOrder, 0, len1 - 1, postOrder, 0, len2 - 1, map);
    }


    // 9 3 15 20 7  中序  左  头 右
    // 9 15 7 20 3  后序  左  右 头
    // 0 1  2  3 4
    // 左树

    public TreeNode process2(int[] inOrder, int l1, int r1, int[] postOrder, int l2, int r2, Map<Integer, Integer> map) {
        if (l2 > r2) {
            return null;
        }
        if (l2 == r2) {
            return new TreeNode(postOrder[l2]);
        }
        // 在中序遍历中找到r2所在的位置
        int index = map.get(postOrder[r2]);
        // 头节点
        TreeNode cur = new TreeNode(postOrder[r2]);
        // 左树的范围就是 l1到index-1 右树的范围就是 index+1到r1
        // 对于后序来说 总共有 index - l1个数
        // 后序的范围是
        cur.left = process2(inOrder, l1, index - 1, postOrder, l2, l2 + index - l1 - 1, map);
        cur.right = process2(inOrder, index + 1, r1, postOrder, l2 + index - l1, r2 - 1, map);
        return cur;
    }

    public static void main(String[] args) {
        Code106_BuildTree code106_buildTree = new Code106_BuildTree();
        int[] inOrder = {9, 3, 15, 20, 7};
        int[] postOrder = {9, 15, 7, 20, 3};
        TreeNode treeNode = code106_buildTree.buildTree(inOrder, postOrder);
        System.out.println(treeNode);
    }
}
