package leetcode75;

/**
 * 给你一棵根为 root 的二叉树，请你返回二叉树中好节点的数目。
 * <p>
 * 「好节点」X 定义为：从根到该节点 X 所经过的节点中，没有任何节点的值大于 X 的值。
 */
public class Code1448 {

    int ans = 0;

    public int goodNodes(TreeNode root) {

        if (root == null) {
            return 0;
        }
        ans = 0;
        if (root.left == null && root.right == null) {
            return 1; // 如果是叶子节点，返回1
        }
        process2(root, Integer.MIN_VALUE); // 从根节点开始，初始值为根节点的值
        return ans;
    }


    public int process(TreeNode root, int pre) {
        if (root == null) {
            return 0;
        }
        int left = process(root.left, Math.max(pre, root.val));
        int right = process(root.right, Math.max(pre, root.val));
        return left + right + (root.val >= pre ? 1 : 0);
    }

    /**
     * 这个递归也是对的
     *
     * @param root
     * @param pre
     */
    public void process2(TreeNode root, int pre) {
        if (root == null) {
            return;
        } else {
            if (root.val >= pre) {
                ans++;
            }
            process2(root.left, Math.max(pre, root.val));
            process2(root.right, Math.max(pre, root.val));
        }
    }

    public static void main(String[] args) {
        Code1448 code1448 = new Code1448();
        //3,1,4,3,null,1,5
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(3);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(5);
        System.out.println(code1448.goodNodes(root)); // 输出 4
    }

}
