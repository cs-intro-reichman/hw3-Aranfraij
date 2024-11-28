// A collection of functions for handling anagrams.
import java.util.HashMap;

public class Anagram {
    public static void main(String[] args) {
        // Basic Anagram Operations Tests
        System.out.println("Testing Basic Anagram Operations:");
        System.out.println(isAnagram("silent", "listen"));  // true
        System.out.println(isAnagram("William Shakespeare", "I am a weakish speller")); // true
        System.out.println(isAnagram("Madam Curie", "Radium came")); // true

        // Advanced Anagram Operations Tests
        System.out.println("Testing Advanced Anagram Operations:");
        System.out.println(preProcess("What? No way!", true));  // "what no way"
        System.out.println(preProcess("Multiple    Spaces!", true));  // "multiple    spaces"
        System.out.println(randomAnagram("silent"));  // Random anagram of "silent"
    }

    // Returns true if the two given strings are anagrams, false otherwise.
    public static boolean isAnagram(String str1, String str2) {
        str1 = preProcess(str1, false); // Strip spaces for basic operations
        str2 = preProcess(str2, false); // Strip spaces for basic operations

        if (str1.length() != str2.length()) {
            return false;
        }

        HashMap<Character, Integer> charCount = new HashMap<>();
        for (char c : str1.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        for (char c : str2.toCharArray()) {
            if (!charCount.containsKey(c) || charCount.get(c) == 0) {
                return false;
            }
            charCount.put(c, charCount.get(c) - 1);
        }

        return true;
    }

    // Preprocesses a string based on the given mode.
    // If preserveSpaces is true, spaces are retained. Otherwise, spaces are removed.
    public static String preProcess(String str, boolean preserveSpaces) {
        StringBuilder ans = new StringBuilder();
        str = str.toLowerCase();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 'a' && c <= 'z') {
                ans.append(c); // Retain alphabetic characters
            } else if (preserveSpaces && c == ' ') {
                ans.append(c); // Retain spaces if mode is true
            }
        }
        return ans.toString();
    }

    // Returns a random anagram of the given string.
    public static String randomAnagram(String str) {
        str = preProcess(str, true); // Preserve spaces for advanced operations
        char[] chars = str.toCharArray();
        for (int i = chars.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
        return new String(chars);
    }
}
