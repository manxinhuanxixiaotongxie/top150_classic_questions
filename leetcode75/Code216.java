package leetcode75;

import java.util.ArrayList;
import java.util.List;

/**
 * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
 * <p>
 * 只使用数字1到9
 * 每个数字 最多使用一次
 * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
 */
public class Code216 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        // 相加之和为n  k个数
        // 只使用数字1-9 每个数组最多使用一次
        // 相加之和最多为54
        if (n > 54 || n < 1 || k < 1 || k > 9) {
            return List.of();
        }
        List<List<Integer>> ans = new ArrayList<>();
        int[] help = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        process(help, 0, n, k, new ArrayList<>(), ans);
        return ans;
    }

    public void process(int[] help, int index, int n, int k, List<Integer> cur, List<List<Integer>> ans) {
        if (index == help.length) {
            // 选k个数 总和为n
            if (n == 0 && k == 0) {
                ans.add(new ArrayList<>(cur));
            }
        } else {
            // 当前index元素不选择
            process(help, index + 1, n, k, cur, ans);
            // 当前index及进行选择
            // 选择的前提剩下的n不能小于当前index位置的数
            if (n >= help[index] && k > 0) {
                cur.add(help[index]);
                process(help, index + 1, n - help[index], k - 1, cur, ans);
                cur.remove(cur.size() - 1); // 回溯
            }
        }
    }
}
