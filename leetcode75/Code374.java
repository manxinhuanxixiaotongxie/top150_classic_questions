package leetcode75;

/**
 * 我们正在玩猜数字游戏。猜数字游戏的规则如下：
 * <p>
 * 我会从 1 到 n 随机选择一个数字。 请你猜选出的是哪个数字。
 * <p>
 * 如果你猜错了，我会告诉你，我选出的数字比你猜测的数字大了还是小了。
 * <p>
 * 你可以通过调用一个预先定义好的接口 int guess(int num) 来获取猜测结果，返回值一共有三种可能的情况：
 * <p>
 * -1：你猜的数字比我选出的数字大 （即 num > pick）。
 * 1：你猜的数字比我选出的数字小 （即 num < pick）。
 * 0：你猜的数字与我选出的数字相等。（即 num == pick）。
 * 返回我选出的数字。
 */
public class Code374 {
    public int guessNumber(int n) {
        int left = 1, right = n;
        while (left < right) { // 循环直至区间左右端点相同
            int mid = left + (right - left) / 2; // 防止计算时溢出
            if (guess(mid) <= 0) {
                right = mid; // 答案在区间 [left, mid] 中
            } else {
                left = mid + 1; // 答案在区间 [mid+1, right] 中
            }
        }
        // 此时有 left == right，区间缩为一个点，即为答案
        return left;
    }

    // 模拟 guess 函数
    public int guess(int num) {
        int pick = 6; // 假设我选的数字是 6
        if (num < pick) {
            return 1; // 猜的数字小了
        } else if (num > pick) {
            return -1; // 猜的数字大了
        } else {
            return 0; // 猜对了
        }
    }
}
