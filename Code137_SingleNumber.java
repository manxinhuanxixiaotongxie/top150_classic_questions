/**
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 * <p>
 * 你必须设计并实现线性时间复杂度的算法且使用常数级空间来解决此问题。
 */
public class Code137_SingleNumber {
    public int singleNumber(int[] nums) {
        int[] help = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                if ((num & (1 << i)) != 0) {
                    help[i]++;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (help[i] % 3 == 0) {
                continue;
            }
            ans |= (1 << i);
        }
        return ans;
    }

    public static void main(String[] args) {
        Code137_SingleNumber code137 = new Code137_SingleNumber();
        int[] nums = {2, 2, 3, 2};
        System.out.println(code137.singleNumber(nums));
    }
}
