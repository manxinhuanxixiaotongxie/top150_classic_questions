import java.util.HashSet;
import java.util.List;

/**
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
 *
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
}
