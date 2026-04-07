import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 */
public class Code080 {
    /**
     * 使用了辅助数组
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int left = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && map.get(nums[i]) == 2) {
                continue;
            } else if (!map.containsKey(nums[i])) {
                map.put(nums[i], 1);
                swap(nums, left++, i);
            } else {
                // 包含但是不为2
                map.put(nums[i], map.get(nums[i]) + 1);
                swap(nums, left++, i);
            }
        }

        return left;
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 数组已经按照升序进行排列了
     * 利用这样的性质
     *
     * @param nums
     * @return
     */
    public int removeDuplicates2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;

        int ans = 1;
        int cnt = 1;
        int index = 1;
        while (index < nums.length) {
            while (index < nums.length && nums[index] == nums[index - 1]) {
                if (cnt < 2) {
                    nums[ans++] = nums[index];
                }
                cnt ++;
                index++;
            }
            cnt = 1;
            if (index < nums.length) {
                nums[ans++] = nums[index++];
            }
        }

        return ans;

    }

}
