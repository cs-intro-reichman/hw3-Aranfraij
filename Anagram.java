public class TestAnagram {
    public static void main(String[] args) {
        System.out.println("Testing preProcess method:");

        // Test 1: Simple lowercase
        boolean test1 = Anagram.preProcess("abc", false).equals("abc");
        System.out.println("Test 1 (simple lowercase): " + (test1 ? "PASS" : "FAIL"));

        // Test 2: Preserve spaces
        boolean test2 = Anagram.preProcess("Hello World!", true).equals("hello world");
        System.out.println("Test 2 (preserve spaces): " + (test2 ? "PASS" : "FAIL"));

        // Test 3: Case conversion
        boolean test3 = Anagram.preProcess("HeLLo", false).equals("hello");
        System.out.println("Test 3 (case conversion): " + (test3 ? "PASS" : "FAIL"));

        // Test 4: Empty string
        boolean test4 = Anagram.preProcess("", false).equals("");
        System.out.println("Test 4 (empty string): " + (test4 ? "PASS" : "FAIL"));

        System.out.println("\nTesting randomAnagram method:");

        // Test 1: Check if randomAnagram result is an anagram
        String str = "silent";
        boolean test5 = Anagram.isAnagram(str, Anagram.randomAnagram(str));
        System.out.println("Test 1 (is anagram): " + (test5 ? "PASS" : "FAIL"));

        // Test 2: Check if randomAnagram result has the same length
        boolean test6 = Anagram.randomAnagram(str).length() == str.length();
        System.out.println("Test 2 (same length): " + (test6 ? "PASS" : "FAIL"));

        // Test 3: Check randomness of randomAnagram
        String random1 = Anagram.randomAnagram(str);
        String random2 = Anagram.randomAnagram(str);
        boolean test7 = !random1.equals(random2); // Likely to pass if truly random
        System.out.println("Test 3 (randomness): " + (test7 ? "PASS" : "FAIL"));
    }
}
