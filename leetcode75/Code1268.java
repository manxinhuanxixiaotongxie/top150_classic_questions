package leetcode75;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * 给你一个产品数组 products 和一个字符串 searchWord ，products  数组中每个产品都是一个字符串。
 * <p>
 * 请你设计一个推荐系统，在依次输入单词 searchWord 的每一个字母后，推荐 products 数组中前缀与 searchWord 相同的最多三个产品。
 * 如果前缀相同的可推荐产品超过三个，请按字典序返回最小的三个。
 * <p>
 * 请你以二维列表的形式，返回在输入 searchWord 每个字母后相应的推荐产品的列表。
 */
public class Code1268 {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Trie trie = new Trie();
        trie.insert(products);
        List<List<String>> ans = new ArrayList<>();
        char[] searchWordChars = searchWord.toCharArray();
        for (int i = 0; i < searchWordChars.length; i++) {
            for (int j = 0; i <= i; j++) {
                // 从j 到i
                Node curNode = trie.root;


            }
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
                    if (curNode.next[curChar] == null) {
                        curNode.next[curChar] = new Node();
                        curNode.map = new TreeMap<>();
                    }
                    curNode.map.get(curNode).add(product);
                    curNode = curNode.next[curChar];
                }
            }
        }
    }

    class Node {
        Node[] next;
        TreeMap<Character, List<String>> map;

        Node() {
            next = new Node[26];
            map = new TreeMap<>();
        }
    }
}
