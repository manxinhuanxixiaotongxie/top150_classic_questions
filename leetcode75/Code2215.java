package leetcode75;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，请你返回一个长度为 2 的列表 answer ，其中：
 *
 * answer[0] 是 nums1 中所有 不 存在于 nums2 中的 不同 整数组成的列表。
 * answer[1] 是 nums2 中所有 不 存在于 nums1 中的 不同 整数组成的列表。
 * 注意：列表中的整数可以按 任意 顺序返回。
 *
 */
public class Code2215 {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set1.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            set2.add(nums2[i]);
        }
        List<List<Integer>> ans = new ArrayList<>();

        List<Integer> left = new ArrayList<>();
        for (Integer num : set1) {
            if (!set2.contains(num)) {
                left.add(num);
            }
        }

        List<Integer> right = new ArrayList<>();
        for (Integer num : set2) {
            if (!set1.contains(num)) {
                right.add(num);
            }
        }
        ans.add(left);
        ans.add(right);
        return ans;
    }
}
