package leetcode75;

import java.util.LinkedList;

public class Code1161 {
    public int maxLevelSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 宽度有限遍历
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        int ans = 0;
        int level = 0;
        int max = Integer.MIN_VALUE;

        while (!list.isEmpty()) {
            int size = list.size();
            int sum = 0;
            level++;
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = list.pollFirst();
                if (treeNode.left != null) {
                    list.addLast(treeNode.left);
                }
                if (treeNode.right != null) {
                    list.addLast(treeNode.right);
                }
                sum += treeNode.val;
            }
            if (sum > max) {
                ans = level;
                max = sum;
            }
        }
        return ans;
    }
}
