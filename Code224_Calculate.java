import java.util.Deque;
import java.util.LinkedList;

/**
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * <p>
 * 注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 *
 */
public class Code224_Calculate {

    public int calculate(String s) {
        Deque<Integer> ops = new LinkedList<Integer>();
        ops.push(1);
        int sign = 1;

        int ret = 0;
        int n = s.length();
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == ' ') {
                i++;
            } else if (s.charAt(i) == '+') {
                sign = ops.peek();
                i++;
            } else if (s.charAt(i) == '-') {
                sign = -ops.peek();
                i++;
            } else if (s.charAt(i) == '(') {
                ops.push(sign);
                i++;
            } else if (s.charAt(i) == ')') {
                ops.pop();
                i++;
            } else {
                long num = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                ret += sign * num;
            }
        }
        return ret;
    }

    /**
     * 通用递归
     *
     * @param s
     * @return
     */
    public int calculate2(String s) {
        return process(s.replace(" ", "").toCharArray(), 0)[0];
    }

    public int[] process(char[] str, int index) {
        if (index == str.length) return new int[]{0, str.length};
        int ans = 0;
        int num = 0;
        // 默认是+号
        int sign = 1;
        while (index < str.length && str[index] != ')') {
            char cur = str[index];
            // 讨论可能性
            // 当前位置可能是数字 可能是+ - 可能是（
            if (cur >= '0' && cur <= '9') {
                // 是数字
                num = num * 10 + (cur - '0');
                index++;
            } else if (cur == '(') {
                // 如果左括号的话
                int[] next = process(str, index + 1);
                num = next[0];
                index = next[1] + 1;
            } else if (cur == '+') {
                // 如果是加号的话
//                int[] next = process(str, index + 1);
//                ans = ans + next[0];
//                index = next[1] + 1;
                // 结算上一个数字
                ans = sign == 1 ? ans + num : ans - num;
                num = 0;
                sign = 1;
                index++;
            } else if (cur == '-') {
                // 如果是减号的话
//                int[] next = process(str, index + 1);
//                ans = ans - next[0];
//                index = next[1] + 1;
                ans = sign == 1 ? ans + num : ans - num;
                num = 0;
                sign = 0;
                index++;
            }
        }
        ans = sign == 1 ? ans + num : ans - num;

        return new int[]{ans, index};
    }

    /**
     * 补充题目：
     * leetcode 227
     * 如果有乘除应该怎么处理
     *
     * @param s
     * @return
     */
    public int calculate3(String s) {
        return process3(s.replace(" ", "").toCharArray(), 0)[0];
    }

    public int[] process3(char[] str, int index) {
        if (index == str.length) return new int[]{0, str.length};
        // 模拟队列  尾巴进 头部出
        Deque<Integer> queue = new LinkedList<>();
        int res = 0;
        int num = 0;
        char sign = '+';
        while (index < str.length && str[index] != ')') {
            char cur = str[index];
            if (cur == ' ') {
                index++;
                continue;
            }
            if (Character.isDigit(cur)) {
                num = num * 10 + (cur - '0');
            } else if (cur == '(') {
                // 计算到与之匹配的有括号的位置
                int[] next = process3(str, index + 1);
                num = next[0];
                index = next[1];
            }
            // 只能是符号了
            // index来到最后一个位置也需要进行结算
            if (cur == '+' || cur == '-' || cur == '*' || cur == '/' || index == str.length - 1) {
                switch (sign) {
                    case '+':
                        queue.offerLast(num);
                        break;
                    case '-':
                        queue.offerLast(-num);
                        break;
                    case '*':
                        queue.offerLast(queue.pollLast() * num);
                        break;
                    case '/':
                        queue.offerLast(queue.pollLast() / num);
                        break;

                }
                sign = cur;
                num = 0;
            }
            index++;
            // 写在这里有问题 为什么
            //每次迭代都会重置 num，包括处理完 ( 之后。导致括号内的结果还没被结算
//            num = 0;
        }
        for (int i : queue) {
            res += i;
        }
        return new int[]{res, index};
    }

}
