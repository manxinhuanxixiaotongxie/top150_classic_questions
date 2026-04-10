import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * <p>
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 */
public class Code128 {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int max = 1;
        /**
         * 注意：遍历元素的时候，要遍历哈希集合，而不是 nums！如果 nums=[1,1,1,…,1,2,3,4,5,…]（前一半都是 1），遍历 nums 的做法会导致每个 1 都跑一个 O(n) 的循环，总的循环次数是 O(n
         * 2
         *  )，会超时。
         *
         *
         */
        for (int num : set) {
            if (set.contains(num - 1) ) {
                continue;
            }
            int cur = 1;
            int curNum = num;
            while (set.contains(curNum + 1)) {
                cur++;
                curNum++;
            }
            max = Math.max(max, cur);
            /**
             * 如果已经找到一条链路大于等于size的一半  那么不可能再找到一条链路比这个长度还大
             */
            if ((max << 1) >= set.size()) {
                break;
            }
        }
        return max;
    }
}
