/**
 * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
 * <p>
 * 如果可以，返回 true ；否则返回 false 。
 * <p>
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 */
public class Code383_CanConstruct {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] index = new int[26];
        char[] magazineChars = magazine.toCharArray();
        for (char c : magazineChars) {
            index[c - 'a']++;
        }
        char[] ransomNoteChars = ransomNote.toCharArray();
        for (char c : ransomNoteChars) {
            if (index[c - 'a'] == 0) {
                return false;
            } else {
                index[c - 'a']--;
            }
        }
        return true;
    }
}
