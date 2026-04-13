import java.util.HashSet;
import java.util.List;

/**
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
 * <p>
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 *
 */
public class Code139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> dicts = new HashSet<>(wordDict);
        return process(s, 0, dicts);
    }


    public boolean process(String s, int index, HashSet<String> dicts) {
        if (index == s.length()) return true;
        for (int i = index; i < s.length(); i++) {
            // 暴力遍历从index到s.length()的所有子串
            if (dicts.contains(s.substring(index, i + 1)) && process(s, i + 1, dicts)) {
                return true;
            }
        }
        return false;
    }

    public boolean wordBreak2(String s, List<String> wordDict) {
        HashSet<String> dicts = new HashSet<>(wordDict);
        // process改成动态规划
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[n] = true;
        for (int index = n - 1; index >= 0; index--) {
            for (int i = index; i < n; i++) {
                if (dicts.contains(s.substring(index, i + 1)) && dp[i + 1]) {
                    dp[index] = true;
                    break;
                }
            }
        }
        return dp[0];
    }

    /**
     * 使用前缀树加速
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak3(String s, List<String> wordDict) {
        TrieTree root = new TrieTree();
        for (String word : wordDict) {
            root.insert(word);
        }
        // process改成动态规划
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[n] = true;
        for (int index = n - 1; index >= 0; index--) {
            for (int i = index; i < n; i++) {
                if (root.contains(s.substring(index, i + 1)) && dp[i + 1]) {
                    dp[index] = true;
                    break;
                }
            }
        }
        return dp[0];
    }


    // 使用前缀树优化contains行为
    class TrieTree {
        //
        TrieNode root;

        TrieTree() {
            root = new TrieNode(' ');
        }

        public void insert(String word) {
            if (word == null || word.length() == 0) return;
            char[] str = word.toCharArray();
            TrieNode cur = root;
            for (int i = 0; i < str.length; i++) {
                if (cur.next[str[i] - 'a'] == null) {
                    cur.next[str[i] - 'a'] = new TrieNode(str[i]);
                }
                cur.pass++;
                cur = cur.next[str[i] - 'a'];
            }
            cur.pass++;
            cur.end++;
        }

        public boolean contains(String word) {
            if (word == null || word.length() == 0) return false;
            TrieNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                if (cur.next[word.charAt(i) - 'a'] == null) {
                    return false;
                }
                cur = cur.next[word.charAt(i) - 'a'];
            }
            return cur.end > 0;
        }
    }

    // 题目给定了限制 只有小写的英文字母构成
    class TrieNode {
        TrieNode[] next;
        char value;
        int pass;
        int end;

        TrieNode(char value) {
            next = new TrieNode[26];
            this.value = value;
        }
    }
}
