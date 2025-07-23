package leetcode75;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 */
public class Code017 {

    char[][] help = {
            {}, {}, {'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'},
            {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}
    };

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return ans;
        }
        process(digits, 0, "", ans);
        return ans;
    }

    public void process(String digits, int index, String path, List<String> ans) {
        if (index == digits.length()) {
            ans.add(path);
        } else {
            char[] chars = help[digits.charAt(index) - '0'];
            for (char c : chars) {
                process(digits, index + 1, path + c, ans);
            }
        }
    }
}
