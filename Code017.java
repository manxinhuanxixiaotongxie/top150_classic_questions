import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 */
public class Code017 {

    static char[][] help = null;

    static {
        help = new char[][]{
                {}, {}, {'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'},
                {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'},
                {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}
        };
    }

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits == null || digits.length() == 0) return ans;
        if (help[digits.charAt(0) - '0'] == null) return ans;
        process(digits.toCharArray(), digits.length(), 0, ans, "");
        return ans;
    }

    public void process(char[] digits, int n, int index, List<String> res, String path) {
        if (index == n) {
            res.add(path);
        } else {
            char cur = digits[index];
            char[] choose = help[cur - '0'];
            for (int i = 0; i < choose.length; i++) {
                path += choose[i];
                process(digits, n, index + 1, res, path); // index+1 才是下一个数字位置，i 只是字母表下标
                path = path.substring(0, path.length() - 1); // 回溯：去掉最后一个字符
            }
        }
    }
}
