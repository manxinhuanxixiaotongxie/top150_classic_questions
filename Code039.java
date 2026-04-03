import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates
 * 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * <p>
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * <p>
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 */
public class Code039 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        process(candidates, target, 0, candidates.length, new ArrayList<>(), res);
        return res;
    }

    public void process(int[] nums, int rest, int index, int n, List<Integer> cur, List<List<Integer>> ans) {
        if (index == n) {
            if (rest == 0) {
                ans.add(new ArrayList<>(cur));
            }
        } else {
            // 当前数选1 个 两个 三个
            for (int times = 0; times * nums[index] <= rest; times++) {
                for (int i = 0; i < times; i++) {
                    cur.add(nums[index]);
                }
                process(nums, rest - times * nums[index], index + 1, n, cur, ans);
                // 移除
                for (int i = 0; i < times; i++) {
                    cur.remove(cur.size() - 1);
                }
            }
        }
    }
}
