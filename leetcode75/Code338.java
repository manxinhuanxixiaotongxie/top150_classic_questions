package leetcode75;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 进阶：
 *
 * 很容易就能实现时间复杂度为 O(n log n) 的解决方案，你可以在线性时间复杂度 O(n) 内用一趟扫描解决此问题吗？
 * 你能不使用任何内置函数解决此问题吗？（如，C++ 中的 __builtin_popcount ）
 *
 */
public class Code338 {
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        Map<Integer, Integer> times = new HashMap<>();
        for (int i = 0; i <= n; i++) {
            int temp = i;
            while (temp != 0) {
                if (times.containsKey(temp)) {
                    ans[i] += times.get(temp);
                    break;
                }
                ans[i] += temp & 1;
                temp >>= 1;
            }
            times.put(i, ans[i]);
        }
        return ans;
    }
}
