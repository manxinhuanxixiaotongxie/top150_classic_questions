package leetcode75;

import java.util.Arrays;

/**
 * 给你两个正整数数组 spells 和 potions ，长度分别为 n 和 m ，其中 spells[i] 表示第 i 个咒语的能量强度，potions[j] 表示第 j 瓶药水的能量强度。
 * <p>
 * 同时给你一个整数 success 。一个咒语和药水的能量强度 相乘 如果 大于等于 success ，那么它们视为一对 成功 的组合。
 * <p>
 * 请你返回一个长度为 n 的整数数组 pairs，其中 pairs[i] 是能跟第 i 个咒语成功组合的 药水 数目。
 *
 * 提示：
 *
 * n == spells.length
 * m == potions.length
 * 1 <= n, m <= 10^5
 * 1 <= spells[i], potions[i] <= 10^5
 * 1 <= success <= 10^10
 */
public class Code2300 {

    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int N = spells.length;
        int[] ans = new int[N];
        Arrays.sort(potions);
        for (int i = 0; i < N; i++) {
            // long div = (((success ) / spells[i])); 这样会出现无法整除的现象 比如10/3 = 3 实际要找到大于3 等于3是不行的
            long div = (((success - 1) / spells[i]));
            if (div < potions[potions.length - 1]) {
                ans[i] = potions.length - getGreatSize2(potions, (int) div);
            } else {
                ans[i] = 0;
            }
        }
        return ans;
    }

    public int getGreatSize2(int[] position, int div) {
        int left = 0;
        int right = position.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
//            if (position[mid] < div) {
            if (position[mid] <= div) {
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return left;
    }


    public int getGreatSize(int[] position, int div) {
        int left = -1;
        int right = position.length;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            // 二分查找
            // 找的是大于div最左侧的位置
            if (position[mid] <= div) {
                left = mid;
            } else if (position[mid] > div) {
                right = mid;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        Code2300 code2300 = new Code2300();
        int[] spells = {1, 2, 3, 4, 5, 6, 7};
        int[] potions = {1, 2, 3, 4, 5, 6, 7};
        long success = 25;
        int[] result = code2300.successfulPairs(spells, potions, success);
        for (int res : result) {
            System.out.print(res + " ");
        }
    }
}
