import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]]
 * 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 *
 */
public class Code015 {
    /**
     * 三数之和
     * <p>
     * 超时
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // 必须包含nums[i]位置
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int next = -nums[i];
            // 在后续位置找到和为next的两个数
            Map<Integer, Integer> map = new HashMap<>();
            List<List<Integer>> list = new ArrayList<>();
            for (int j = i + 1; j < nums.length; j++) {
                if (map.containsKey(next - nums[j])) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[j]);
                    temp.add(next - nums[j]);
                    list.add(temp);
                    ans.add(temp);
                    while (j + 1 < nums.length && nums[j] == nums[j + 1]) {
                        j++;
                    }
                }
                map.put(nums[j], j);

            }
        }
        return ans;
    }

    /**
     * 使用双指针
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum2(int[] nums) {
        // 必须包含nums[i]位置
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 使用双指针
            int left = i + 1;
            int right = nums.length - 1;
            int target = -nums[i];
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum > target) {
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    // 相等结算
                    if (right == nums.length - 1 || nums[right] != nums[right + 1]) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[left]);
                        temp.add(nums[right]);
                        ans.add(temp);
                    }
                    right--;
                }
            }
        }
        return ans;
    }
}
