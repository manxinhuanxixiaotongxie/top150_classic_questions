package leetcode75;

import java.util.ArrayList;
import java.util.List;

/**
 * 请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。
 *
 * 提示：
 *
 * 给定的两棵树结点数在 [1, 200] 范围内
 * 给定的两棵树上的值在 [0, 200] 范围内
 *
 */
public class Code872 {
    /**
     * 深度优先遍历
     *
     * @param root1
     * @param root2
     * @return
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        process(root1, list1);
        process(root2, list2);
        boolean result = true;
        if (list1.size() != list2.size()) {
            result = false;
        } else {
            for (int i = 0; i < list1.size(); i++) {
                if (!list1.get(i).equals(list2.get(i))) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    public void process(TreeNode root, List<Integer> list) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            list.add(root.val);
        } else {
            process(root.left, list);
            process(root.right, list);
        }
    }
}
