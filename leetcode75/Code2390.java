package leetcode75;

import java.util.Stack;

/**
 * 给你一个包含若干星号 * 的字符串 s 。
 * <p>
 * 在一步操作中，你可以：
 * <p>
 * 选中 s 中的一个星号。
 * 移除星号 左侧 最近的那个 非星号 字符，并移除该星号自身。
 * 返回移除 所有 星号之后的字符串。
 * <p>
 * 注意：
 * <p>
 * 生成的输入保证总是可以执行题面中描述的操作。
 * 可以证明结果字符串是唯一的。
 */
public class Code2390 {
    public String removeStars(String s) {
        char[] str = s.toCharArray();
        boolean[] delete = new boolean[str.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '*') {
                if (stack.isEmpty()) {
                    delete[i] = true;
                } else {
                    delete[stack.pop()] = true;
                    delete[i] = true;
                }
            } else {
                stack.push(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length; i++) {
            if (!delete[i]) {
                sb.append(str[i]);
            }
        }
        return sb.toString();
    }

    public String removeStars2(String s) {
        char[] str = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '*') {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(str[stack.pop()]);
        }
        sb.reverse();
        return sb.toString();
    }
}
