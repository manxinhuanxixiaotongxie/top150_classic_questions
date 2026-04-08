public class Code088 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int r1 = m - 1;
        int r2 = n - 1;
        int r = nums1.length - 1;
        while (r1 >= 0 && r2 >= 0) {
            if (nums1[r1] > nums2[r2]) {
                nums1[r--] = nums1[r1--];
            }else {
                nums1[r--] = nums2[r2--];
            }
        }
        while (r1 >= 0) {
            nums1[r--] = nums1[r1--];
        }
        while (r2 >= 0) {
            nums1[r--] = nums2[r2--];
        }
    }
}
