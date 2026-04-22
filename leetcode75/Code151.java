package leetcode75;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
 *
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 *
 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
 *
 * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 *
 */
public class Code151 {
    public String reverseWords(String s) {
        String trim = s.trim();
        String[] split = s.split("\\s+");
        int l = 0;
        int r = split.length - 1;
        while (l < r) {
            swap(split, l++, r--);
        }
        StringBuilder sb = new StringBuilder();
        for (String word : split) {
            sb.append(word).append(" ");
        }
        return sb.toString().trim();
    }

    public String reverseWords2(String s) {
        s = s.trim();
        List<String> list = Arrays.asList(s.split("\\s+"));
        Collections.reverse(list);
        return String.join(" ", list);
    }

    private void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
