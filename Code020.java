import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 *
 */
public class Code020 {
    /**
     * 方法1：计数器方案 —— 无法AC
     * <p>
     * 根本原因：用三个独立计数器只能保证"每种括号左右数量相等"，
     * 但丢失了括号之间的"先后顺序/嵌套关系"，无法判断右括号是否匹配最近的同类左括号。
     * <p>
     * 反例1： "([)]"
     * ( -> smallTimes=1
     * [ -> biggerTimes=1
     * ) -> smallTimes>0，放行，smallTimes=0
     * ] -> biggerTimes>0，放行，biggerTimes=0
     * 最终三个计数器都为0，方法1返回 true；但实际是交叉嵌套，正确答案是 false。
     * <p>
     * 反例2： "[({)}]" 同样会被误判为合法。
     * <p>
     * 结论：多种括号嵌套必须使用栈，栈天然记录"最近一个未匹配的左括号"，
     * 遇到右括号时必须与栈顶匹配，从而同时校验数量 + 嵌套顺序。见方法2。
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        if (s.length() % 2 == 1) {
            return false;
        }
        // 代表左括号的数量
        int smallTimes = 0;
        int middleTimes = 0;
        int biggerTimes = 0;
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++) {
            char c = str[i];
            if (c == '(') {
                smallTimes++;
            } else if (c == ')') {
                if (smallTimes == 0) {
                    return false;
                }
                smallTimes--;
            } else if (c == '{') {
                middleTimes++;
            } else if (c == '}') {
                if (middleTimes == 0) {
                    return false;
                }
                middleTimes--;
            } else if (c == '[') {
                biggerTimes++;
            } else if (c == ']') {
                if (biggerTimes == 0) {
                    return false;
                }
                biggerTimes--;
            }
        }
        return smallTimes == 0 && middleTimes == 0 && biggerTimes == 0;
    }

    public boolean isValid2(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
