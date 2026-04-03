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
        if (s == null || s.isEmpty() || words == null || words.length == 0) return res;
        int wordLength = words[0].length() * words.length;
        if (s.length() < wordLength) return res;

        Set<String> wordList = new HashSet<>();
        process(words, 0, wordList);
        for (String word : wordList) {
            if (s.contains(word)) {
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
     *
     * 示例 1：
     *
     * 输入：s = "barfoothefoobarman", words = ["foo","bar"]
     *
     * 定长滑动窗口：
     *
     * 设一个单词的的长度为wordLen,由于题目保证words中所有单词的长度都相同
     * 所有单词的长度之和（窗口长度）windowLen = wordLen * m 其中m是words的长度
     *
     * 示例1的wordLen =3  窗口长度windowLen = 3*2 = 6
     *
     * 我们要在s中找到一个长为windowLen的子串t 满足：把t均分成m个长为wordLen的单词
     * 统计每个单词出现的次数cnt（这是一个hash表）以及words中每个单词出现次数targetCnt(这个也是一个hash表)
     * 满足cnt = targetCnt
     *
     * 核心思路
     * 示例 1 的 s=barfoothefoobarman，从第一个子串 barfoo 到另一个子串 foothe，少了一个 bar，多了一个 the，中间的 foo 是不变的，不需要重复统计。这种场景非常适合定长滑动窗口算法。
     *
     * 我们可以枚举第一个单词首字母是 s[0]=b 还是 s[1]=a 还是 s[2]=r。只需要枚举这 3 个，因为 s[3]=f 的情况在 s[0]=b 的定长滑动窗口中计算了，其余 s[4],s[5],… 同理。
     *
     * 假设从 i=0 开始滑窗，示例 1 依次遍历到的单词下标区间为 [0,3),[3,6),[6,9),[9,12),[12,15),[15,18)，把每个单词看成一个元素，我们相当于在如下列表中跑定长滑动窗口：
     *
     * [bar,foo,the,foo,bar,man]
     * 一般地，我们需要跑 wordLen 次起点不同的定长滑动窗口，窗口左端点分别为：
     *
     * 0,wordLen,2⋅wordLen,3⋅wordLen,…
     * 1,1+wordLen,1+2⋅wordLen,1+3⋅wordLen,…
     * 2,2+wordLen,2+2⋅wordLen,2+3⋅wordLen,…
     * ……
     * wordLen−1,2⋅wordLen−1,3⋅wordLen−1,4⋅wordLen−1,…
     * ⚠注意：无需考虑起点为 wordLen 的滑动窗口，它已经在起点为 0 的滑动窗口中计算了。对于起点大于 wordLen 的滑动窗口也同理。
     *
     * 定长滑窗套路
     * 我总结成三步：入-更新-出。
     *
     * 入：枚举进入窗口的单词 inWord，把 inWord 的出现次数加一，如果加一之前 inWord 的出现次数等于其在 targetCnt 中的出现次数，那么加一之后就过多了，我们用一个变量 overload 统计过多的单词个数。⚠注意：这包括不在 words 中的单词。
     * 更新：更新答案。如果 overload=0，那么符合要求，把窗口左端点加入答案。
     * 出：窗口最左边的单词 outWord 离开窗口，减少 outWord 的出现次数。如果减少后 outWord 的出现次数等于其在 targetCnt 中的出现次数，那么 outWord 从「过多」变成「符合要求」，把 overload 减一。
     * 以上三步适用于所有定长滑窗题目。
     *
     * 小优化：如果 s 的长度小于 windowLen，可以提前返回空列表。由于不影响实际运行时间，所以代码没有写这个优化。
     *
     * [sol-Java]
     * class Solution {
     *     public List<Integer> findSubstring(String s, String[] words) {
     *         int wordLen = words[0].length(); // 一个单词的长度
     *         int windowLen = wordLen * words.length; // 所有单词的总长度，即窗口大小
     *
     *         // 目标：窗口中的单词出现次数必须与 targetCnt 完全一致
     *         Map<String, Integer> targetCnt = new HashMap<>();
     *         for (String w : words) {
     *             targetCnt.merge(w, 1, Integer::sum); // targetCnt[w]++
     *         }
     *
     *         List<Integer> ans = new ArrayList<>();
     *         // 枚举第一个窗口的左端点，做 wordLen 次起点不同的滑动窗口
     *         for (int start = 0; start < wordLen; start++) {
     *             Map<String, Integer> cnt = new HashMap<>();
     *             int overload = 0; // 统计过多的单词个数（包括不在 words 中的单词）
     *             // 枚举窗口最后一个单词的右开端点
     *             for (int right = start + wordLen; right <= s.length(); right += wordLen) {
     *                 // 1. inWord 进入窗口
     *                 String inWord = s.substring(right - wordLen, right);
     *                 // 下面 cnt[inWord]++ 后，inWord 的出现次数过多
     *                 if (cnt.getOrDefault(inWord, 0).equals(targetCnt.getOrDefault(inWord, 0))) {
     *                     overload++;
     *                 }
     *                 cnt.merge(inWord, 1, Integer::sum); // cnt[inWord]++
     *
     *                 int left = right - windowLen; // 窗口第一个单词的左端点
     *                 if (left < 0) { // 窗口大小不足 windowLen
     *                     continue;
     *                 }
     *
     *                 // 2. 更新答案
     *                 // 如果没有超出 targetCnt 的单词，那么也不会有少于 targetCnt 的单词
     *                 if (overload == 0) {
     *                     ans.add(left);
     *                 }
     *
     *                 // 3. 窗口最左边的单词 outWord 离开窗口，为下一轮循环做准备
     *                 String outWord = s.substring(left, left + wordLen);
     *                 cnt.merge(outWord, -1, Integer::sum); // cnt[outWord]--
     *                 if (cnt.get(outWord).equals(targetCnt.getOrDefault(outWord, 0))) {
     *                     overload--;
     *                 }
     *             }
     *         }
     *
     *         return ans;
     *     }
     * }

     * 复杂度分析
     * 时间复杂度：O((n+m)⋅wordLen)，其中 n 是 s 的长度，m 是 words 的长度，wordLen 是 words[i] 的长度。初始化 targetCnt 需要 O(m⋅wordLen) 的时间，做 O(wordLen) 次定长滑窗，每次 O(n) 时间。
     * 空间复杂度：O(m⋅wordLen)。注意窗口长度为 O(m⋅wordLen)，所以 cnt 消耗的空间是 O(m⋅wordLen)。
     * 更多相似题目，见下面滑动窗口题单的「一、定长滑动窗口」。
     *
     * 分类题单
     * 如何科学刷题？
     *
     * 滑动窗口与双指针（定长/不定长/单序列/双序列/三指针/分组循环）
     * 二分算法（二分答案/最小化最大值/最大化最小值/第K小）
     * 单调栈（基础/矩形面积/贡献法/最小字典序）
     * 网格图（DFS/BFS/综合应用）
     * 位运算（基础/性质/拆位/试填/恒等式/思维）
     * 图论算法（DFS/BFS/拓扑排序/基环树/最短路/最小生成树/网络流）
     * 动态规划（入门/背包/划分/状态机/区间/状压/数位/数据结构优化/树形/博弈/概率期望）
     * 常用数据结构（前缀和/差分/栈/队列/堆/字典树/并查集/树状数组/线段树）
     * 数学算法（数论/组合/概率期望/博弈/计算几何/随机算法）
     * 贪心与思维（基本贪心策略/反悔/区间/字典序/数学/思维/脑筋急转弯/构造）
     * 链表、树与回溯（前后指针/快慢指针/DFS/BFS/直径/LCA）
     * 字符串（KMP/Z函数/Manacher/字符串哈希/AC自动机/后缀数组/子序列自动机）
     * 我的题解精选（已分类）
     *
     * 欢迎关注 B站@灵茶山艾府
     *
     *
     * <p>
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
            // 当前超标的单词种类数
            int overload = 0;
            // 枚举窗口最后一个单词的右端点+1
            for (int right = start + wordLen; right <= s.length(); right += wordLen) {
                // 1. inWord 进入窗口
                String inWord = s.substring(right - wordLen, right);
                // 下面 cnt[inWord]++ 后，inWord 的出现次数过多

                //含义：如果当前窗口中 inWord 的数量已经达到或等于目标要求的数量，那么当你再把这个 inWord 加进去时，这个单词的词频就会“溢出”或“超标”。
                //
                //overload 的作用：它统计的是“当前窗口中有多少种单词的出现次数超过了目标要求”。
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
                // 已经出了窗口 但是还相等 超标的数量减1 扣减之后如果 cnt[outWord] 恰好等于 targetCnt[outWord]，说明 outWord 从“过多”变成“符合要求”，所以 overload 减一。
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
