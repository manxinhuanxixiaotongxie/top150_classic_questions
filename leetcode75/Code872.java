package leetcode75;

import java.util.ArrayList;
import java.util.List;

public class Code872 {
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
