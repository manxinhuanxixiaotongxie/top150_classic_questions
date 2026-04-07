import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个单词数组 words 和一个长度 maxWidth ，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
 * <p>
 * 你应该使用 “贪心算法” 来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
 * <p>
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 * <p>
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 * <p>
 * 注意:
 * <p>
 * 单词是指由非空格字符组成的字符序列。
 * 每个单词的长度大于 0，小于等于 maxWidth。
 * 输入单词数组 words 至少包含一个单词。
 */
public class Code068_FullJustify {
    /**
     * 官解
     *
     * @param words
     * @param maxWidth
     * @return
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<String>();
        int right = 0, n = words.length;
        while (true) {
            int left = right; // 当前行的第一个单词在 words 的位置
            int sumLen = 0; // 统计这一行单词长度之和
            // 循环确定当前行可以放多少单词，注意单词之间应至少有一个空格
            while (right < n && sumLen + words[right].length() + right - left <= maxWidth) {
                sumLen += words[right++].length();
            }

            // 当前行是最后一行：单词左对齐，且单词之间应只有一个空格，在行末填充剩余空格
            if (right == n) {
                StringBuffer sb = join(words, left, n, " ");
                sb.append(blank(maxWidth - sb.length()));
                ans.add(sb.toString());
                return ans;
            }

            int numWords = right - left;
            int numSpaces = maxWidth - sumLen;

            // 当前行只有一个单词：该单词左对齐，在行末填充剩余空格
            if (numWords == 1) {
                StringBuffer sb = new StringBuffer(words[left]);
                sb.append(blank(numSpaces));
                ans.add(sb.toString());
                continue;
            }

            // 当前行不只一个单词
            int avgSpaces = numSpaces / (numWords - 1);
            int extraSpaces = numSpaces % (numWords - 1);
            StringBuffer sb = new StringBuffer();
            sb.append(join(words, left, left + extraSpaces + 1, blank(avgSpaces + 1))); // 拼接额外加一个空格的单词
            sb.append(blank(avgSpaces));
            sb.append(join(words, left + extraSpaces + 1, right, blank(avgSpaces))); // 拼接其余单词
            ans.add(sb.toString());
        }
    }

    // blank 返回长度为 n 的由空格组成的字符串
    public String blank(int n) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; ++i) {
            sb.append(' ');
        }
        return sb.toString();
    }

    // join 返回用 sep 拼接 [left, right) 范围内的 words 组成的字符串
    public StringBuffer join(String[] words, int left, int right, String sep) {
        StringBuffer sb = new StringBuffer(words[left]);
        for (int i = left + 1; i < right; ++i) {
            sb.append(sep);
            sb.append(words[i]);
        }
        return sb;
    }

    /**
     * 滑动窗口
     *
     * @param words
     * @param maxWidth
     * @return
     */
    public List<String> fullJustify2(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int n = words.length;
        for (int i = 0; i < n; ) {
            int start = i; // 这一行第一个单词的下标
            int sumLen = words[i].length(); // 第一个单词的长度
            for (i++; i < n && sumLen + words[i].length() + 1 <= maxWidth; i++) {
                sumLen += words[i].length() + 1; // 单词之间至少要有一个空格
            }

            int extraSpaces = maxWidth - sumLen; // 这一行剩余未分配的空格个数
            int gaps = i - start - 1; // 这一行单词之间的空隙个数（单词个数减一）

            // 特殊情况：如果只有一个单词，或者是最后一行，那么左对齐，末尾补空格
            if (gaps == 0 || i == n) {
                // 末尾补空格
                String row = join(words, start, i, " ") + " ".repeat(extraSpaces);
                ans.add(row);
                continue;
            }

            // 一般情况：把 extraSpaces 个空格均匀分配到 gaps 个空隙中（靠左的空格更多）
            int avg = extraSpaces / gaps;
            int rem = extraSpaces % gaps;
            // +1 表示加上单词之间已有的一个空格
            String spaces = " ".repeat(avg + 1);
            // 前 rem 个空隙多一个空格
            String row = join(words, start, start + rem + 1, spaces + ' ') +
                    spaces + join(words, start + rem + 1, i, spaces);
            ans.add(row);
        }
        return ans;
    }


    static void main() {
            String[] words = {"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"};
            int maxWidth = 20;
            Code068_FullJustify code = new Code068_FullJustify();
            List<String> ans = code.fullJustify2(words, maxWidth);
            for (String s : ans) {
                System.out.println(s);
            }
    }
}
