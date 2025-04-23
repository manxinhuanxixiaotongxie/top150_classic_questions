/**
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * <p>
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * <p>
 * 进阶：
 * <p>
 * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 * <p>
 * 致谢：
 * <p>
 * 特别感谢 @pbrother 添加此问题并且创建所有测试用例。
 */
public class Code392_IsSubsequence {
    /**
     * 双指针
     * 分别从s 与t的0位置出发
     * 要求如果s是t的子序列 那么 s的字符必须都要在t上找到才行
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        char[] ss = s.toCharArray();
        char[] tt = t.toCharArray();
        int i = 0, j = 0;
        while (i < ss.length && j < tt.length) {
            if (ss[i] == tt[j]) {
                i++;
            }
            j++;
        }
        return i == ss.length;
    }
}
