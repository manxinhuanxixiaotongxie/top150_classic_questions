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
     * 无法AC
     *
     * 计数器方案只能判断"每种括号数量是否匹配"，
     * 无法判断"右括号是否对应最近的左括号"，这个问题必须用栈来解决。需要将方法1的逻辑改成栈方案：
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
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
