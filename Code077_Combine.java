import java.util.ArrayList;
import java.util.List;

public class Code077_Combine {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k > n || k <= 0) {
            return res;
        }
        process(1, n, k, new ArrayList<>(), res);
        return res;
    }

    public void process(int begin, int n, int rest, List<Integer> cur, List<List<Integer>> res) {
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
