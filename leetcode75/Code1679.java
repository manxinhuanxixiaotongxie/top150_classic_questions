package leetcode75;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums 和一个整数 k 。
 * <p>
 * 每一步操作中，你需要从数组中选出和为 k 的两个整数，并将它们移出数组。
 * <p>
 * 返回你可以对数组执行的最大操作数。
 */
public class Code1679 {
    public int maxOperations(int[] nums, int k) {
        // 数值、数量
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.merge(nums[i], 1, Integer::sum);
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 0) {
                continue;
            }
            int key = entry.getKey();
            if (map.containsKey(k - key)) {
                if (key == k - key && map.get(k - key) < 2) {
                    continue; // 如果 k == key，且数量小于2，则无法配对
                }
                if (key == k - key) {
                    ans += map.get(key) / 2; // 如果 k == key，则只能配对相同的数
                } else {
                    ans += Math.min(map.get(k - key), map.get(key)); // 否则取两者数量的最小值
                }
                map.put(k - key, 0);
                map.put(key, 0);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Code1679 code1679 = new Code1679();
        int[] nums = {2, 5, 4, 4, 1, 3, 4, 4, 1, 4, 4, 1, 2, 1, 2, 2, 3, 2, 4, 2};
        int k = 3;
        int result = code1679.maxOperations(nums, k);
        System.out.println("最大操作次数: " + result); // 输出: 最大操作次数: 2
    }
}
