public class Code129_SumNumbers {
    int sum = 0;

    public int sumNumbers(TreeNode root) {
        sum = 0;
        process(root, 0);
        return sum;
    }

    public void process(TreeNode root, int pre) {
        if (root.left == null && root.right == null) {
            sum += pre * 10 + root.val;
        } else {
            if (root.left != null) {
                process(root.left, pre * 10 + root.val);
            }
            if (root.right != null) {
                process(root.right, pre * 10 + root.val);
            }
        }
    }
}
