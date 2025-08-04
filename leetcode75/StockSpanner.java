package leetcode75;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 设计一个算法收集某些股票的每日报价，并返回该股票当日价格的 跨度 。
 * <p>
 * 当日股票价格的 跨度 被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。
 */
public class StockSpanner {
    Stack<Integer> stack;
    Map<Integer, Integer> map;
    int index = 0;

    public StockSpanner() {
        // 100 80 60 70 60 75 85
        // 栈顶到栈底  由小到大 找出左边离我最近比我大
        stack = new Stack<>();
        map = new HashMap<>();
        index = 0;
    }

    public int next(int price) {
        int ans = 1;
        map.put(index, price);
        while (!stack.isEmpty() && map.get(stack.peek()) <= price) {
            Integer pop = stack.pop();
            ans +=1;
        }

        stack.push(index++);
        return ans;
    }
}
