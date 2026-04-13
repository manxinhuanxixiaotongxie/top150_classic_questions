import java.util.Stack;

/**
 * 给你一个字符串数组 tokens ，表示一个根据 逆波兰表示法 表示的算术表达式。
 * <p>
 * 请你计算该表达式。返回一个表示表达式值的整数。
 *
 */
public class Code150 {
    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if ("+".equals(tokens[i])) {
                // 弹出两个数进行计算
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = num1 + num2;
                stack.push(String.valueOf(res));
            } else if ("-".equals(tokens[i])) {
                // 弹出两个数进行计算
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = num1 - num2;
                stack.push(String.valueOf(res));
            } else if ("*".equals(tokens[i])) {
                // 弹出两个数进行计算
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = num1 * num2;
                stack.push(String.valueOf(res));
            } else if ("/".equals(tokens[i])) {
                // 弹出两个数进行计算
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = num1 / num2;
                stack.push(String.valueOf(res));
            } else {
                stack.push(tokens[i]);
            }
        }

        return Integer.parseInt(stack.pop());
    }
}
