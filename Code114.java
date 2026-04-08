import java.util.List;

public class Code114 {
    // 常规解法 求先序遍历
    public void flatten1(TreeNode root) {
        if (root == null) return;
        List<TreeNode> list = new java.util.ArrayList<>();
        // 先序遍历
        process(root, list);
        // 将list转化为链表
        for (int i = 0; i < list.size() - 1; i++) {
            TreeNode current = list.get(i);
            current.left = null; // 左子树置空
            current.right = list.get(i + 1); // 右子树指向下一个节点
        }
        // 最后一个节点的右子树也要置空
        list.get(list.size() - 1).left = null;
        list.get(list.size() - 1).right = null;
    }

    public void process(TreeNode root, List<TreeNode> list) {
        if (root == null) return;
        // 先序遍历
        list.add(root);
        process(root.left, list);
        process(root.right, list);
    }

    /**
     * 使用morris遍历改写
     *
     * 注意：不能改右指针   使用左指针链接之后进行翻转
     *
     * @param root
     */
    public void flatten2(TreeNode root) {
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null) {
            if (cur.left == null) {
                if (pre != null) {
                    pre.left = cur;
                }
                pre = cur;
                cur = cur.right;
            }else {
                TreeNode left = cur.left;
                while (left.right != null && left.right != cur) {
                    left = left.right;
                }
                if (left.right == null) {
                    if (pre != null) {
                        pre.left = cur;
                    }
                    pre = cur;
                    left.right = cur;   // 设置回溯指针
                    cur = cur.left;     // 向左走
                }else {
                    left.right = null;  // 断开回溯指针
                    cur = cur.right;
                }
            }
        }
        cur = root;
        TreeNode next;
        while (cur != null) {
            next = cur.left;
            cur.left = null;
            cur.right = next;
            cur = next;
        }
    }
}
