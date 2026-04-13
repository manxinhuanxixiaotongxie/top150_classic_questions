import java.util.HashMap;
import java.util.Map;

/**
 * 直线上最多的点数
 * <p>
 * 给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
 */
public class Code149 {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) return n;
        int ans = 2;
        for (int i = 0; i < n; i++) {
            Map<String, Integer> map = new HashMap<>();
            int duplicate = 0; // 与 i 重合的点
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];
                if (dx == 0 && dy == 0) {
                    duplicate++;
                    continue;
                }
                // 规范化斜率，保证相同斜率得到相同 key
                int g = Math.abs(gcd(dx, dy));
                dx /= g;
                dy /= g;
                // 统一方向：让 dx > 0，或 dx == 0 时 dy > 0
                if (dx < 0) { dx = -dx; dy = -dy; }
                else if (dx == 0 && dy < 0) { dy = -dy; }
                String key = dx + "/" + dy;
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
            if (map.isEmpty()) {
                // 所有点都和 i 重合
                ans = Math.max(ans, duplicate + 1);
            } else {
                for (int cnt : map.values()) {
                    // cnt 条斜率相同的点 + duplicate 个重合点 + i 本身
                    ans = Math.max(ans, cnt + duplicate + 1);
                }
            }
        }
        return ans;
    }

    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

}
