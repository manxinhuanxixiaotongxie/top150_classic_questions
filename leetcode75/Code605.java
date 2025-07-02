package leetcode75;

/**
 * 假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
 * <p>
 * 给你一个整数数组 flowerbed 表示花坛，由若干 0 和 1 组成，其中 0 表示没种植花，1 表示种植了花。另有一个数 n ，
 * 能否在不打破种植规则的情况下种入 n 朵花？能则返回 true ，不能则返回 false 。
 */
public class Code605 {
    /**
     * 超时
     *
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed == null || flowerbed.length == 0) return true;

        return process(flowerbed, 0, n);
    }

    public boolean process(int[] flowerbed, int index, int n) {
        if (index == flowerbed.length) {
            if (n > 0) return false;
            // 检查

            for (int i = 0; i < index - 1; i++) {
                if (flowerbed[i] == 1 && flowerbed[i + 1] == 1) return false;
            }
            return true;
        } else {
            boolean ans = false;
            if (flowerbed[index] == 0) {
                // 当前位置可以放 也可以不放
                ans |= process(flowerbed, index + 1, n);
                flowerbed[index] = 1;
                ans |= process(flowerbed, index + 1, n - 1);
                flowerbed[index] = 0;
            } else {
                ans |= process(flowerbed, index + 1, n);
            }
            return ans;
        }
    }

    /**
     * 贪心
     *
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers2(int[] flowerbed, int n) {
        if (flowerbed == null || flowerbed.length == 0) return true;

        for (int i = 0; i < flowerbed.length - 1; i++) {
            if (flowerbed[i] == 1 && flowerbed[i + 1] == 1) return false;
        }
        // 第一个位置是0
        int index = 0;
        while (index < flowerbed.length) {
            // 什么样的位置才能放呢
            // 当前位置是0并且前面一个位置后面一个位置是0
            if (index - 1 >= 0 && index + 1 < flowerbed.length) {
                if (flowerbed[index] == 0 && flowerbed[index - 1] == 0 && flowerbed[index + 1] == 0) {
                    // 可以放
                    flowerbed[index] = 1;
                    n--;
                }
            } else if (index + 1 < flowerbed.length && index == 0 && flowerbed[index] == 0 && flowerbed[index + 1] == 0) {
                // 如果index是0
                flowerbed[index] = 1;
                n--;
            } else if (index - 1 >= 0 && index == flowerbed.length - 1 && flowerbed[index] == 0 && flowerbed[index - 1] == 0) {
                // 如果index是最后一个位置
                flowerbed[index] = 1;
                n--;
            } else if (index == 0 && flowerbed.length == 1 && flowerbed[index] == 0) {
                // 如果只有一个位置
                flowerbed[index] = 1;
                n--;
            }
            index++;
        }
        if (n > 0) return false;

        return true;
    }

    public static void main(String[] args) {
        Code605 code605 = new Code605();
        int[] flowerbed = {1, 0, 0, 0, 0, 1};
        int n = 2;
        boolean result = code605.canPlaceFlowers(flowerbed, n);
        System.out.println(result); // 输出 true
    }

}
