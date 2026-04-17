import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个  无重复元素 的 有序 整数数组 nums 。
 * <p>
 * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表 。也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
 * <p>
 * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
 * <p>
 * "a->b" ，如果 a != b
 * "a" ，如果 a == b
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 20
 * -2^31 <= nums[i] <= 2^31 - 1
 * nums 中的所有值都 互不相同
 * nums 按升序排列
 */
public class Code228_SummaryRanges {
    public List<String> summaryRanges(int[] nums) {
        int n = nums.length;
        int l = 0;
        int r = 1;
        List<String> result = new ArrayList<>();

        while (r <= n) {
            while (r < n && nums[r] - nums[r - 1] == 1) {
                r++;
            }
            // 结算从l到R 左闭右开
            if (l == r - 1) {
                result.add(nums[l] + "");
                l++;
                r++;
            } else {
                result.add(nums[l] + "->" + nums[r - 1]);
                l = r;
                r++;
            }
        }
        return result;
    }

    /**
     * 直接滑动
     *
     * @param nums
     * @return
     */
    public List<String> summaryRanges2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        int n = nums.length;
        int l = 0;
        int end = 0;
        List<String> result = new ArrayList<>();
        while (l < n) {
            while (end < n - 1 && nums[end] + 1 == nums[end + 1]) {
                end++;
            }
            if (l == end) {
                result.add(nums[l] + "");
            } else {
                result.add(nums[l] + "->" + nums[end]);
            }
            end++;
            l = end;
        }
        return result;
    }

    /**
     * 分组循环
     *
     * @param nums
     * @return
     */
    public List<String> summaryRanges3(int[] nums) {

        int n = nums.length;
        int i = 0;
        List<String> result = new ArrayList<>();
        while (i < n) {
            int start = i;
            while (i < n - 1 && nums[i] + 1 == nums[i + 1]) {
                i++;
            }
            if (start == i) {
                result.add(nums[start] + "");
            } else {
                result.add(nums[start] + "->" + nums[i]);
            }
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        Code228_SummaryRanges code228 = new Code228_SummaryRanges();
        int[] nums = {0, 1, 2, 4, 5, 7};
        System.out.println(code228.summaryRanges(nums));
    }
}
