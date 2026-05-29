/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
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
        return strs[0].substring(0, index);
    }

    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        // 随便找一个
        String str = strs[0];
        StringBuilder sb = new StringBuilder();
        char[] chars = str.toCharArray();
        TrieTree trie = new TrieTree();
        for (String string : strs) {
            trie.insert(string);
        }
        int n = strs.length;
        TrieNode cur = trie.root;
        for (char c : chars) {
            if (cur.next[c - 'a'] != null && cur.next[c - 'a'].pass == n) {
                sb.append(c);
                cur = cur.next[c - 'a'];
                continue;
            }
            break;
        }

        return sb.toString();
    }

    class TrieTree {
        TrieNode root;

        TrieTree() {
            root = new TrieNode();
        }

        public void insert(String word) {
            if (word == null || word.isEmpty()) {
                return;
            }
            TrieNode cur = root;
            char[] str = word.toCharArray();
            for (char c : str) {
                if (cur.next[c - 'a'] == null) {
                    cur.next[c - 'a'] = new TrieNode();
                }
                cur.pass++;
                cur = cur.next[c - 'a'];
            }
            cur.pass++;
        }
    }

    class TrieNode {
        TrieNode[] next;
        int pass;

        TrieNode() {
            next = new TrieNode[26];
            pass = 0;
        }
    }
}
