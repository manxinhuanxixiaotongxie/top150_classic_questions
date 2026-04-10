public class Code124 {
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root).maxPathSum;
    }

    public Info process(TreeNode root) {
        if (root == null) {
            // 因为存在负数 不能直接返回0
            return null;
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        // 开始整合信息
        if (leftInfo == null && rightInfo == null) {
            // 两者都为空
            // 只有一种可能 就是当前节点
            return new Info(root.val, root.val);
        } else if (leftInfo == null) {
            // 右树不为空
            // 单边的值
            int maxSideValue = Math.max(rightInfo.maxSideValue + root.val, root.val);
            // 最大值
            int maxPathSum = Math.max(maxSideValue, rightInfo.maxPathSum);
            return new Info(maxSideValue, maxPathSum);
        } else if (rightInfo == null) {
            // 左树不为空
            // 单边的值
            int maxSideValue = Math.max(leftInfo.maxSideValue + root.val, root.val);
            // 最大值
            int maxPathSum = Math.max(maxSideValue, leftInfo.maxPathSum);
            return new Info(maxSideValue, maxPathSum);
        } else {
            int maxSideValue = Math.max(root.val, Math.max(leftInfo.maxSideValue + root.val, rightInfo.maxSideValue + root.val));
            int maxPathSum = Math.max(maxSideValue, Math.max(Math.max(leftInfo.maxPathSum, rightInfo.maxPathSum), leftInfo.maxSideValue + root.val + rightInfo.maxSideValue));
            return new Info(maxSideValue, maxPathSum);
        }
    }

    class Info {
        // 从当前节点出发 最大单边路径和
        int maxSideValue;
        // 不从当前节点出发 但是是以当前节点作为的头节点 能够获取到的最大路径和
        int maxPathSum;

        Info(int maxSideValue, int maxPathSum) {
            this.maxSideValue = maxSideValue;
            this.maxPathSum = maxPathSum;
        }
    }
}
