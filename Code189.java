import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右轮转 1 步: [7,1,2,3,4,5,6]
 * 向右轮转 2 步: [6,7,1,2,3,4,5]
 * 向右轮转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 */
public class Code189 {
    public void rotate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        k = (k % n);
        for (int i = 0; i < n; i++) {
            map.put((i + k) % n, nums[i]);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            nums[entry.getKey()] = entry.getValue();
        }
    }

    /**
     * 双指针
     *
     * @param nums
     * @param k
     */
    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        k = (k % n);
        // 整体翻转
        reverse(nums, 0, n - 1);
        // 0-k-1进行翻转
        reverse(nums, 0, k - 1);
        // k -n-1进行翻转
        reverse(nums, k, n - 1);
    }

    public void reverse(int[] nums, int left, int right) {

        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    static void main() {
        Code189 code189 = new Code189();
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        code189.rotate(nums, 3);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
