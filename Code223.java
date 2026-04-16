/**
 * 补充题目：
 * <p>
 * 给你 二维 平面上两个 由直线构成且边与坐标轴平行/垂直 的矩形，请你计算并返回两个矩形覆盖的总面积。
 * <p>
 * 每个矩形由其 左下 顶点和 右上 顶点坐标表示：
 * <p>
 * 第一个矩形由其左下顶点 (ax1, ay1) 和右上顶点 (ax2, ay2) 定义。
 * 第二个矩形由其左下顶点 (bx1, by1) 和右上顶点 (bx2, by2) 定义。
 */
public class Code223 {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        // 求和再减
        int sum = 0;
        // 第一个矩形的面积
        sum += (ax2 - ax1) * (ay2 - ay1);
        // 第二个矩形的面积
        sum += (by2 - by1) * (bx2 - bx1);
        // 重叠的面积
        // 左下角重叠的横坐标
        int x1 = Math.max(ax1, bx1);
        int x2 = Math.min(ax2, bx2);
        int y1 = Math.max(ay1, by1);
        int y2 = Math.min(ay2, by2);
        if (x2 >= x1 && y2 >= y1) {
            sum -= (x2 - x1) * (y2 - y1);
        }
        return sum;
    }

    static void main() {

    }
}
