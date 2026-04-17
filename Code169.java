import java.util.List;

/**
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 */
public class Code169 {
    public int majorityElement(int[] nums) {
        int times = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            // 守擂台
            if (times == 0) {
                ans = nums[i];
                times += 1;
            } else if (nums[i] == ans) {
                times += 1;
            } else {
                times--;
            }
        }
        return ans;
    }

    /**
     * 补充题目：
     * 多数元素 II
     * <p>
     * 摩尔投票法
     * <p>
     * leetcode229
     * <p>
     * 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
     * 尝试设计时间复杂度为O(n)，空间复杂度为O(1)的算法。
     * <p>
     * 满足条件的元素最多只有两个元素 如果有两个元素是大于1/3的 那么剩下的长度一定是小于1/3的 不然的话
     * 就会超过数组的长度
     *
     */
    public List<Integer> majorityElement2(int[] nums) {
        int nums1 = 0;
        int nums2 = 0;
        int nums1Times = 0;
        int nums2Times = 0;
        for (int num : nums) {
            /**
             * 在摩尔投票中，“判断当前数字是否等于已有候选人”的优先级，
             * 必须高于“判断计数器是否为 0 并更换候选人”。
             */
//            if (nums1Times == 0) {
//                nums1 = num;
//                nums1Times++;
//            } else if (nums2Times == 0) {
//                nums2 = num;
//                nums2Times++;
//            } else if (num == nums1) {
//                nums1Times++;
//            } else if (num == nums2) {
//                nums2Times++;
//            } else {
//                // 两者都不为0 并且都不相等
//                // 抵消
//                nums1Times--;
//                nums2Times--;
//            }
            if (num == nums1) {
                nums1Times++;
            } else if (num == nums2) {
                nums2Times++;
            } else if (nums1Times == 0) {
                nums1 = num;
                nums1Times++;
            } else if (nums2Times == 0) {
                nums2 = num;
                nums2Times++;
            } else {
                // 两者都不为0 并且都不相等
                // 抵消
                nums1Times--;
                nums2Times--;
            }
        }
        int count1 = 0;
        int count2 = 0;
        for (int num : nums) {
            if (num == nums1) {
                count1++;
            } else if (num == nums2) {
                count2++;
            }
        }
        List<Integer> ans = new java.util.ArrayList<>();
        if (count1 > nums.length / 3) {
            ans.add(nums1);
        }
        if (count2 > nums.length / 3) {
            ans.add(nums2);
        }
        return ans;
    }
}
