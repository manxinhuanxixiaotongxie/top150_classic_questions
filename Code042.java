/**
 * 接雨水问题
 */
public class Code042 {
    public int trap(int[] height) {
        // 相等也要弹出 结算的结果不影响最终的结果
        int ans = 0;
        int n = height.length;
        int leftMax = height[0];
        int rightMax = height[n - 1];
        int left = 1;
        int right = n - 1;
        // 双指针
        while (left <= right) {
            //if (height[left] < height[right]) {
            if (leftMax < rightMax) {
                ans += Math.max(0, leftMax - height[left]);
                leftMax = Math.max(leftMax, height[left]);
                left++;
            } else {
                ans += Math.max(0, rightMax - height[right]);
                rightMax = Math.max(rightMax, height[right]);
                right--;
            }

        }

        return ans;

    }
}
