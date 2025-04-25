public class Code006_Convert {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        // 一组多少个数
        int num = numRows + numRows - 2;
        // 总共有多少组
        int group = s.length() / num + (s.length() % num == 0 ? 0 : 1);
        // 总共有多少列
        int col = group * (numRows - 1);
        char[][] chars = new char[numRows][col];
        // 按列填充
        int index = 0;
        for (int i = 0; i <= col; ) {
            for (int j = 0; j < numRows; j++) {
                if (index >= s.length()) {
                    break;
                }
                chars[j][i] = s.charAt(index++);
            }
            if (index >= s.length()) {
                break;
            }
            int tempR = numRows - 2;
            int tempC = i + 1;
            while (tempR > 0) {
                if (index >= s.length()) {
                    break;
                }
                chars[tempR][tempC] = s.charAt(index++);
                tempR--;
                tempC++;
            }
            i = tempC;
        }
        // 遍历二维数组
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < col; j++) {
                if (chars[i][j] != '\u0000') {
                    sb.append(chars[i][j]);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Code006_Convert code006_convert = new Code006_Convert();
        String result = code006_convert.convert("ABCDE", 4);
        System.out.println(result); // Output: "PAHNAPLSIIGYIR"
    }
}
