package leetcode75;

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
