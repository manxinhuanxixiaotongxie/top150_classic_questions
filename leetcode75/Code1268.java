package leetcode75;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * 给你一个产品数组 products 和一个字符串 searchWord ，products  数组中每个产品都是一个字符串。
 * <p>
 * 请你设计一个推荐系统，在依次输入单词 searchWord 的每一个字母后，推荐 products 数组中前缀与 searchWord 相同的最多三个产品。
 * 如果前缀相同的可推荐产品超过三个，请按字典序返回最小的三个。
 * <p>
 * 请你以二维列表的形式，返回在输入 searchWord 每个字母后相应的推荐产品的列表。
 *
 */
public class Code1268 {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Trie trie = new Trie();
        trie.insert(products);
        List<List<String>> ans = new ArrayList<>();
        char[] searchWordChars = searchWord.toCharArray();
        Node cur = trie.root;
        for (int i = 0; i < searchWordChars.length; i++) {
            char curChar = searchWordChars[i];
            List<String> list = new ArrayList<>();
            if (cur.next[curChar - 'a'] == null) {
                // 当某个前缀不存在时，用了 continue 继续循环，但 cur 没有更新，后续字符还会用旧的 cur 去查，导致结果错误。
                ans.add(list);
                for (int k = i + 1; k < searchWordChars.length; k++) {
                    ans.add(new ArrayList<>());
                }
                break;
            }
            cur = cur.next[curChar - 'a'];
            TreeSet<String> set = new TreeSet<>(cur.set);
            for (int j = 0; j < 3 && !set.isEmpty(); j++) {
                list.add(set.pollFirst());
            }
            ans.add(list);
        }
        return ans;
    }


    class Trie {
        Node root;

        Trie() {
            root = new Node();
        }

        public void insert(String[] products) {
            for (String product : products) {
                char[] productStr = product.toCharArray();
                Node curNode = root;
                for (char curChar : productStr) {
                    if (curNode.next[curChar - 'a'] == null) {
                        curNode.next[curChar - 'a'] = new Node();
                    }
                    // 必须要先移动后塞入 保证最后一个节点能够被塞入
                    curNode = curNode.next[curChar - 'a'];
                    curNode.set.add(product);
                }
            }
        }
    }

    class Node {
        Node[] next;
        TreeSet<String> set;

        Node() {
            next = new Node[26];
            set = new TreeSet<>();
        }
    }
}
