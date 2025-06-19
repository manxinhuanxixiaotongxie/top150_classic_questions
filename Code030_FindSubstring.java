import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个字符串 s 和一个字符串数组 words。 words 中所有字符串 长度相同。
 * <p>
 * s 中的 串联子串 是指一个包含  words 中所有字符串以任意顺序排列连接起来的子串。
 * <p>
 * 例如，如果 words = ["ab","cd","ef"]， 那么 "abcdef"， "abefcd"，"cdabef"，
 * "cdefab"，"efabcd"， 和 "efcdab" 都是串联子串。 "acdbef" 不是串联子串，因为他不是任何 words 排列的连接。
 * 返回所有串联子串在 s 中的开始索引。你可以以 任意顺序 返回答案。
 */
public class Code030_FindSubstring {
    /**
     * 无法完全AC 超时
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<Integer>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return res;
        int wordLength = words[0].length() * words.length;
        if (s.length() < wordLength) return res;

        Set<String> wordList = new HashSet<>();
        process(words, 0, wordList);
        for (String word : wordList) {
            if (s.contains(word)){
                int index = s.indexOf(word);
                while (index != -1) {
                    res.add(index);
                    index = s.indexOf(word, index + 1);
                }
            }
        }
        return res;
    }

    public void process(String[] words, int index, Set<String> wordList) {
        if (index == words.length) {
            StringBuilder sb = new StringBuilder();
            for (String word : words) {
                sb.append(word);
            }
            wordList.add(sb.toString());
        } else {
            for (int i = index; i < words.length; i++) {
                // 交换
                swap(words, index, i);
                process(words, index + 1, wordList);
                // 恢复
                swap(words, index, i);
            }
        }
    }

    public void swap(String[] words, int i, int j) {
        String temp = words[i];
        words[i] = words[j];
        words[j] = temp;
    }

    /**
     * 滑动窗口
     *
     * 找到一个子串 由于Word的长度都是相同的 因此题目就是在s中找到一个子串
     * 然后将子串拆分成words.length个单词 统计每个的单词出现的次数 以及words中每个单词出现的次数
     * 满足这两者次数相等
     *
     * 参考：https://leetcode.cn/problems/substring-with-concatenation-of-all-words/solutions/3691292/30-ci-ding-chang-hua-dong-chuang-kou-pyt-5vgx/?envType=study-plan-v2&envId=top-interview-150
     *
     *
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring2(String s, String[] words) {
        int wordLen = words[0].length(); // 一个单词的长度
        int windowLen = wordLen * words.length; // 所有单词的总长度，即窗口大小

        // 目标：窗口中的单词出现次数必须与 targetCnt 完全一致
        Map<String, Integer> targetCnt = new HashMap<>();
        for (String w : words) {
            targetCnt.merge(w, 1, Integer::sum); // targetCnt[w]++
        }

        List<Integer> ans = new ArrayList<>();
        // 枚举窗口起点，做 wordLen 次滑动窗口
        /**
         *
         * 我们可以枚举第一个单词首字母是 s[0]=b 还是 s[1]=a 还是 s[2]=r。只需要枚举这 3 个，
         * 因为 s[3]=f 的情况在 s[0]=b 的定长滑动窗口中计算了，其余 s[4],s[5],⋯ 同理
         *
         */
        for (int start = 0; start < wordLen; start++) {
            Map<String, Integer> cnt = new HashMap<>();
            int overload = 0;
            // 枚举窗口最后一个单词的右端点+1
            for (int right = start + wordLen; right <= s.length(); right += wordLen) {
                // 1. inWord 进入窗口
                String inWord = s.substring(right - wordLen, right);
                // 下面 cnt[inWord]++ 后，inWord 的出现次数过多
                if (cnt.getOrDefault(inWord, 0).equals(targetCnt.getOrDefault(inWord, 0))) {
                    overload++;
                }
                cnt.merge(inWord, 1, Integer::sum); // cnt[inWord]++

                int left = right - windowLen; // 窗口第一个单词的左端点
                if (left < 0) { // 窗口大小不足 windowLen
                    continue;
                }

                // 2. 更新答案
                // 如果没有超出 targetCnt 的单词，那么也不会有少于 targetCnt 的单词
                if (overload == 0) {
                    ans.add(left);
                }

                // 3. 窗口最左边的单词 outWord 离开窗口，为下一轮循环做准备
                String outWord = s.substring(left, left + wordLen);
                cnt.merge(outWord, -1, Integer::sum); // cnt[outWord]--
                if (cnt.get(outWord).equals(targetCnt.getOrDefault(outWord, 0))) {
                    overload--;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Code030_FindSubstring code030 = new Code030_FindSubstring();
        String s = "wordgoodgoodgoodbestword";
        String[] words = {"word", "good", "best", "good"};
        List<Integer> result = code030.findSubstring(s, words);
        System.out.println(result); // 输出: [0, 9]
    }
}
