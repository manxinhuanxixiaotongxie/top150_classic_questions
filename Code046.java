import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Code046 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) return res;
        process(nums, 0, res);
        return res;
    }

    /**
     * 一种比较好的尝试
     *
     */
    public void process(int[] nums, int index, List<List<Integer>> res) {
        if (index == nums.length) {
            ArrayList<Integer> cur = new ArrayList<>();
            for (int num : nums) cur.add(num);
            res.add(cur);
        } else {
            for (int i = index; i < nums.length; i++) {
                swap(nums, index, i);
                process(nums, index + 1, res);
                swap(nums, index, i);
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
