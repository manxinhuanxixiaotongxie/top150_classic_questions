public class Code055 {
    public boolean canJump(int[] nums) {
        int len = nums.length;
        int mostRight = 0;
        int mostNextRight = 0;
        for (int i = 0; i < len - 1; i++) {
            mostNextRight = Math.max(mostNextRight, nums[i] + i);
            if (i == mostRight) {
                if (i > mostNextRight) {
                    return false;
                }
                mostRight = mostNextRight;
            }
        }
        return true;
    }
}
