import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


/**
 * 字典 wordList 中从单词 beginWord 到 endWord 的 转换序列 是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk：
 * <p>
 * 每一对相邻的单词只差一个字母。
 * 对于 1 <= i <= k 时，每个 si 都在 wordList 中。注意， beginWord 不需要在 wordList 中。
 * sk == endWord
 * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，返回 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。
 * 如果不存在这样的转换序列，返回 0 。
 */
public class Code127_LadderLength {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        wordSet.add(beginWord);
        int ans = 1; // beginWord 本身算第1个词
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                if (word.equals(endWord)) { // poll 之后立即判断，在 for(s:wordList) 外面
                    return ans;
                }
                for (String s : wordList) {
                    if (canTransfer(word, s) && !wordSet.contains(s)) {
                        queue.add(s);
                        wordSet.add(s);
                    }
                }
            }
            ans++;
        }
        return 0;
    }

    public boolean canTransfer(String from, String to) {
        int diff = 0;
        char[] fromStr = from.toCharArray();
        char[] toStr = to.toCharArray();
        for (int i = 0; i < fromStr.length; i++) { // < 不是 <=
            if (fromStr[i] != toStr[i]) {
                diff++;
            }
        }
        return diff == 1;
    }


    public static void main(String[] args) {
        Code127_LadderLength code127 = new Code127_LadderLength();
        String beginWord = "a";
        String endWord = "c";
        List<String> wordList = List.of("a", "b", "c");
        int result = code127.ladderLength(beginWord, endWord, wordList);
        System.out.println(result); // 输出 5
    }

}
