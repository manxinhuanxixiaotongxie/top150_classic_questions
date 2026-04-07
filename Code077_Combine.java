import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * <p>
 * 你可以按 任何顺序 返回答案。
 *
 */
public class Code077_Combine {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k > n || k <= 0) {
            return res;
        }
        process(1, n, k, new ArrayList<>(), res);
        return res;
    }

    public void process(int begin, int n, int rest,
                        List<Integer> cur, List<List<Integer>> res) {
        if (rest == 0) {
            List<Integer> list = new ArrayList<>(cur);
            res.add(list);
        } else {
            for (int i = begin; i <= n; i++) {
                cur.add(i);
                process(i + 1, n, rest - 1, cur, res);
                cur.remove(cur.size() - 1);
            }
        }
    }

    public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k > n || k <= 0) {
            return res;
        }
        process2(n, 1, k, new ArrayList<>(), res);
        return res;
    }

    public void process2(int n, int cur, int restNums, List<Integer> curList, List<List<Integer>> ans) {
        // cur是当前数
        // restNums是剩余的需要选择的数字的数量 最多选k个数字
        // curList是当前路径下的选择的结果
        // ans是答案
        // 我们选择的方案是从1一直选择到n 选够 k个数字
        if (cur == n + 1) {
            if (restNums == 0) {
                ans.add(new ArrayList<>(curList));
            }
        } else if (restNums == 0) {
            ans.add(new ArrayList<>(curList));
        } else {
            // 当前位置不选
            process2(n, cur + 1, restNums, curList, ans);
            // 当前位置选
            curList.add(cur);
            process2(n, cur + 1, restNums - 1, curList, ans);
            curList.remove(curList.size() - 1);
        }
    }

    public static void main(String[] args) {
        Code077_Combine code077_combine = new Code077_Combine();
        List<List<Integer>> result = code077_combine.combine(4, 2);
        System.out.println(result);

        // 这段代码印证了为什么上述的递归不能直接把cur放入res中 必须要新增一个副本才行
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list.add(list1);
        list1.remove(list1.size() - 1);
        System.out.println(list);

    }
}
