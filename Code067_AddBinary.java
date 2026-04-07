/**
 * 给你两个二进制字符串 a 和 b ，以二进制字符串的形式返回它们的和。
 *
 */
public class Code067_AddBinary {

    public String addBinary(String a, String b) {
        char[] aStr = a.toCharArray();
        char[] bStr = b.toCharArray();
        StringBuilder sb = new StringBuilder();
        int add = 0;
        int r1 = aStr.length - 1, r2 = bStr.length - 1;
        while (r1 >= 0 && r2 >= 0) {
            // 这里的加法是字符相加，实际上是ASCII码相加
            // '0'的ASCII码是48，'1'的ASCII码是49

            if (aStr[r1] + bStr[r2] + add - 48 - 48 >= 2) {
                sb.append(aStr[r1] + bStr[r2] + add - 48 - 48 - 2);
                add = 1;
            } else {
                sb.append(aStr[r1] + bStr[r2] - 96 + add);
                add = 0;
            }
            r1--;
            r2--;
        }
        while (r1 >= 0) {
            if (aStr[r1] + add - 48 >= 2) {
                sb.append(aStr[r1] + add - 48 - 2);
                add = 1;
            } else {
                sb.append(aStr[r1] + add - 48);
                add = 0;
            }
            r1--;
        }
        while (r2 >= 0) {
            if (bStr[r2] + add - 48 >= 2) {
                sb.append(bStr[r2] + add - 48 - 2);
                add = 1;
            } else {
                sb.append(bStr[r2] + add - 48);
                add = 0;
            }
            r2--;
        }
        if (add == 1) {
            sb.append(1);
        }
        // 翻转sb
        int l = 0;
        int r = sb.length() - 1;
        char[] charArray = sb.toString().toCharArray();
        while (l < r) {
            // 交换
            char temp = charArray[l];
            charArray[l++] = charArray[r];
            charArray[r--] = temp;
        }
        return String.valueOf(charArray);
    }

    public String addBinary2(String a, String b) {
        char[] str1 = a.toCharArray();
        char[] str2 = b.toCharArray();
        int add = 0;
        int r1 = str1.length - 1, r2 = str2.length - 1;
        StringBuilder sb = new StringBuilder();
        while (r1 >= 0 && r2 >= 0) {
            int sum = str1[r1--] + str2[r2--] - 96 + add;
            sb.insert(0, sum % 2);
            add = sum / 2;
        }
        while (r1 >= 0) {
            int sum = str1[r1--] - 48 + add;
            sb.insert(0, sum % 2);
            add = sum / 2;
        }
        while (r2 >= 0) {
            int sum = str2[r2--] - 48 + add;
            sb.insert(0, sum % 2);
            add = sum / 2;
        }
        if (add == 1) {
            sb.insert(0,1);
        }
        return String.valueOf(sb);
    }


    public static void main(String[] args) {
        String a = "11";
        String b = "1001";
        Code067_AddBinary addBinary = new Code067_AddBinary();
        System.out.printf(addBinary.addBinary2(a, b));
    }

}
