import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 */
public class Code022 {

    /**
     * 比较差的尝试
     * 生成之后进行验证
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis1(int n) {
        List<String> list = new ArrayList<String>();
        process1(list, new char[2 * n], 0, 2 * n);
        return list;
    }

    public void process1(List<String> ans, char[] str, int index, int n) {
        if (index == n) {
            // 检查是否有效
            if (checkValid(str)) {
                ans.add(String.valueOf(str));
            }
        } else {
            // 当前位置可以选择放置左括号或者右括号
            str[index] = '(';
            process1(ans, str, index + 1, n);
            str[index] = ')';
            process1(ans, str, index + 1, n);
        }
    }

    public boolean checkValid(char[] str) {
        Stack<Character> stack = new Stack();
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '(' || str[i] == '{' || str[i] == '[') {
                stack.push(str[i]);
            } else {
                // 当前位置是反向
                // 判断是否匹配
                if (stack.isEmpty()) {
                    return false;
                }
                char pop = stack.pop();
                if (str[i] == ')' && pop != '(') {
                    return false;
                }
                if (str[i] == '{' && pop != '{')
                    return false;
                if (str[i] == '[' && pop != '[')
                    return false;
            }
        }
        return stack.isEmpty();
    }


    /**
     * 比较好的尝试方法
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis2(int n) {
        List<String> list = new ArrayList<String>();
        process2(list, n << 1, "", n, 0);
        return list;
    }

    // 一种比较好的尝试
    // leftRest 左括号剩余的数量 只要左边括号还有就能放组边括号
    // 什么情况下能放右括号 当左括号比有括号数量多的时候 就能放右括号
    // leftMinusRight左括号减去右括号的数量
    public void process2(List<String> list, int n, String path, int leftRest, int leftMinusRight) {
        if (path.length() == n) {
            list.add(path);
        } else {
            if (leftRest > 0) {
                process2(list, n, path + "(", leftRest - 1, leftMinusRight + 1);
            }
            if (leftMinusRight > 0) {
                process2(list, n, path + ")", leftRest, leftMinusRight - 1);
            }
        }
    }

    /**
     * 这种会快一点
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis3(int n) {
        List<String> list = new ArrayList<String>();
        int size = n << 1;
        char[] chars = new char[size];
        process3(list, size, 0, chars, n, 0);
        return list;
    }

    // 一种比较好的尝试
    // leftRest 左括号剩余的数量 只要左边括号还有就能放组边括号
    // 什么情况下能放右括号 当左括号比有括号数量多的时候 就能放右括号
    // leftMinusRight左括号减去右括号的数量
    public void process3(List<String> list, int n, int index, char[] chars, int leftRest, int leftMinusRight) {
        if (index == n) {
            list.add(new String(chars));
        } else {
            if (leftRest > 0) {
                chars[index] = '(';
                process3(list, n, index + 1, chars, leftRest - 1, leftMinusRight + 1);
            }
            if (leftMinusRight > 0) {
                chars[index] = ')';
                process3(list, n, index + 1, chars, leftRest, leftMinusRight - 1);
            }
        }
    }
}
