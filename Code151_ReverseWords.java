public class Code151_ReverseWords {
    public String reverseWords(String s) {
        // 先处理字符串
        if (s == null || s.isEmpty() || s.length() == 1) {
            return s;
        }
        String trim = s.trim();
        String[] split1 = trim.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = split1.length - 1; i >= 0; i--) {
            if (!split1[i].isEmpty()) {
                sb.append(split1[i]).append(" ");
            }
        }
        return sb.toString().trim();
    }

    private void swap(String[] str, int i, int j) {
        String temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }

    public static void main(String[] args) {
        Code151_ReverseWords code151ReverseWords = new Code151_ReverseWords();
        String s = "  hello world!  ";
        String result = code151ReverseWords.reverseWords(s);
        System.out.println(result); // Output: "world! hello"
    }
}
