import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串 s 和一个字符串字典 wordDict ，在字符串 s 中增加空格来构建一个句子，使得句子中所有的单词都在词典中。以任意顺序 返回所有这些可能的句子。
 * <p>
 * 注意：词典中的同一个单词可能在分段中被重复使用多次。
 *
 */
public class Code140 {
    public List<String> wordBreak(String s, List<String> wordDict) {
        TrieTree trieTree = new TrieTree();
        for (String word : wordDict) {
            trieTree.insert(word);
        }
        List<String> ans = new ArrayList<>();
        process(s, 0, trieTree, ans, "");
        return ans;
    }

    public void process(String s, int index, TrieTree tree, List<String> ans, String path) {
        if (index == s.length()) {
            ans.add(path.trim());
        } else {
            for (int i = index; i < s.length(); i++) {
                if (tree.contains(s.substring(index, i + 1))) {
                    process(s, i + 1, tree, ans, path + s.substring(index, i + 1) + " ");
                }
            }
        }
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
