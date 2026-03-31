/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 */
public class Code014 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        int index = 0;
        int minLen = Integer.MAX_VALUE;
        for (String str : strs) {
            minLen = Math.min(minLen, str.length());
        }
        while (index < minLen) {
            for (String str : strs) {
                if (str.charAt(index) != strs[0].charAt(index)) {
                    return index == 0 ? "" : strs[0].substring(0, index);
                }
            }
            index++;

        }
        return strs[0].substring(0,index);
    }
}
