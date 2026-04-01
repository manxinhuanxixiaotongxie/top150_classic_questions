import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 */
public class Code022 {

    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        process(list, n, "", n, 0);
        return list;
    }

    // 一种比较好的尝试
    public void process(List<String> list,int n,String path,int leftRest,int leftMinusRight) {
        if (path.length() == 2 * n) {
            list.add(path);
            return;
        }else {
            if (leftRest > 0) {
                process(list, n, path + "(", leftRest - 1, leftMinusRight + 1);
            }
            if (leftMinusRight > 0) {
                process(list, n, path + ")", leftRest, leftMinusRight - 1);
            }
        }
    }
}
