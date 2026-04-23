package leetcode75;

import java.util.Arrays;
import java.util.Stack;

/**
 * 给定一个整数数组 asteroids，表示在同一行的小行星。数组中小行星的索引表示它们在空间中的相对位置。
 * <p>
 * 对于数组中的每一个元素，其绝对值表示小行星的大小，正负表示小行星的移动方向（正表示向右移动，负表示向左移动）。每一颗小行星以相同的速度移动。
 * <p>
 * 找出碰撞后剩下的所有小行星。碰撞规则：两个小行星相互碰撞，较小的小行星会爆炸。如果两颗小行星大小相同，则两颗小行星都会爆炸。两颗移动方向相同的小行星，永远不会发生碰撞。
 */
public class Code735 {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < asteroids.length; i++) {
            if (asteroids[i] > 0) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && asteroids[stack.peek()] > 0 && asteroids[stack.peek()] < Math.abs(asteroids[i])) {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    if (asteroids[stack.peek()] < 0) {
                        stack.push(i);
                    } else if (asteroids[stack.peek()] == Math.abs(asteroids[i])) {
                        stack.pop(); // 两颗小行星大小相同，均爆炸
                    }
                }
            }
        }
        int n = stack.size();
        int[] result = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            result[i] = asteroids[stack.pop()];
        }
        return result;
    }

    /**
     *
     * 遍历到一颗向左的小行星时，我们需要找到左边最近的未爆炸的小行星。这可以用一个栈维护。
     *
     * 流程：
     * 1.当前节点大于0直接入栈
     * 2.当前节点小于0 分情况讨论
     * 1.栈为空 那么当前小于0 是需要保留的
     * 2.栈不为空 那么就需要计算需要保留的数据了 要爆炸掉所有小于当前节点的绝对值的节点
     * 要注意 如果是相等的话 需要都进行注销
     *
     * @param asteroids
     * @return
     */
    public int[] asteroidCollision2(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for (int num : asteroids) {
            if (num > 0) {
                stack.push(num);
            } else {
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < Math.abs(num)) {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    stack.push(num);
                } else {
                    // 是否反方向相等
                    if (stack.peek() > 0 && stack.peek() == Math.abs(num)) {
                        stack.pop();
                    } else if (stack.peek() < 0) {
                        stack.push(num);
                    }
                }
            }
        }
        int n = stack.size();
        int[] result = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            result[i] = asteroids[stack.pop()];
        }
        return result;
    }

    public int[] asteroidCollision3(int[] asteroids) {
        int[] st = new int[asteroids.length];
        int top = -1; // 栈顶下标
        next:
        for (int x : asteroids) {
            if (x > 0) { // x 向右
                st[++top] = x;
                continue;
            }
            while (top >= 0 && st[top] > 0) { // 栈顶小行星向右
                int t = st[top];
                if (t <= -x) { // 栈顶小行星爆炸
                    top--;
                }
                if (t >= -x) { // x 爆炸
                    // 跳出当前循环 直接开始执行next标签指定的那个外层循环的下一次迭代
                    continue next; // 遍历下一颗小行星
                }
            }
            // x 没有爆炸
            st[++top] = x;
        }
        return Arrays.copyOf(st, top + 1);
    }


    public static void main(String[] args) {
        Code735 code735 = new Code735();
        int[] asteroids = {-2, -1, 1, 2};
        int[] result = code735.asteroidCollision(asteroids);
        for (int asteroid : result) {
            System.out.print(asteroid + " ");
        }
        // Output: 5 10
    }
}
