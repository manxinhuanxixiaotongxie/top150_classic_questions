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
}
