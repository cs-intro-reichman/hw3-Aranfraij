// A collection of functions for handling anagrams.
public class Anagram {
    public static void main(String args[]) {
        // Tests the isAnagram function.
        System.out.println(isAnagram("silent", "listen"));  // true
        System.out.println(isAnagram("William Shakespeare", "I am a weakish speller")); // true
        System.out.println(isAnagram("Madam Curie", "Radium came")); // true
        
        // Tests the randomAnagram function.
        System.out.println(randomAnagram("silent")); // Prints a random anagram
        
        // Performs a stress test of randomAnagram
        Boolean pass = true;
        String str = "this is a stress test";
        for (int i = 0; i < 1000; i++) {
            pass = pass && isAnagram(str, randomAnagram(str));    
        }
        System.out.println(pass); // true if all tests are positive
    }

    // Returns true if the two given strings are anagrams, false otherwise.
    public static boolean isAnagram(String str1, String str2) {
        str1 = preProcess(str1);
        str2 = preProcess(str2);
        if (str1.length() != str2.length()) {
            return false;
        }
        for (int i = 0; i < str1.length(); i++) {
            char c = str1.charAt(i);
            int count1 = 0;
            int count2 = 0;
            for (int j = 0; j < str1.length(); j++) {
                if (str1.charAt(j) == c) count1++;
                if (str2.charAt(j) == c) count2++;
            }
            if (count1 != count2) return false;
        }
        return true;
    }

    // Returns a preprocessed version of the given string: all the letter characters
    // are converted to lower-case, spaces are preserved, and all the other characters 
    // are deleted. For example, the string "What? No way!" becomes "what no way".
    public static String preProcess(String str) {
        String ans = "";
        str = str.toLowerCase(); // Convert to lowercase
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ((c >= 'a' && c <= 'z') || c == ' ') {
                ans += c; // Retain letters and spaces
            }
        }
        return ans;
    }

    // Returns a random anagram of the given string.
    public static String randomAnagram(String str) {
        str = preProcess(str);
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
