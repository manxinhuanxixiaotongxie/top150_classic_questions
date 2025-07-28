package leetcode75;

import java.util.HashMap;
import java.util.Map;

public class Code338 {
    public int[] countBits(int n) {
        int[] ans = new int[n+1];
        Map<Integer,Integer> times = new HashMap<>();
        for (int i = 0; i <=n;i++) {
            int temp = i;
            while (temp !=0) {
                if (times.containsKey(temp)) {
                    ans[i] += times.get(temp);
                    break;
                }
                ans[i] += temp & 1;
                temp >>= 1;
            }
            times.put(i,ans[i]);
        }
        return ans;
    }
}
