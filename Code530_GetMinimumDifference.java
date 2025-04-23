import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Code530_GetMinimumDifference {
    int pre;
    int ans;

    /**
     * 中序遍历
     *
     * @param root
     * @return
     */
    public int getMinimumDifference(TreeNode root) {
        pre = -1;
        ans = Integer.MAX_VALUE;
        process(root);
        return ans;
    }


    public void process(TreeNode root) {
        if (root == null) return;
        process(root.left);
        if (pre == -1) pre = root.val;
        else {
            ans = Math.min(ans, Math.abs(root.val - pre));
            pre = root.val;
        }
        process(root.right);
    }

}
