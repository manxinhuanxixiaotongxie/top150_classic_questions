package leetcode75;

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

    private void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
