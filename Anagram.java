// A collection of functions for handling anagrams.
public class Anagram {
	public static void main(String args[]) {
	      // Tests the isAnagram function.
   		  System.out.println(isAnagram("silent","listen"));  // true
	      System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
	      System.out.println(isAnagram("Madam Curie","Radium came")); // true
	      /Tests the randomAnagram function./
	      System.out.println(randomAnagram("silent")); // Prints a random anagram
	      // Performs a stress test of randomAnagram
	      Boolean pass = true;
	      String str = "this is a stress test";
	      for (int i = 0; i < 1000; i++) {
	          pass = pass && isAnagram(str, randomAnagram(str));	
	      }     
	      System.out.println(pass);	// true if all tests are positive
	   }  

	   // Returns true if the two given strings are anagrams, false otherwise.
	   public static boolean isAnagram(String str1, String str2) {
		   str1 = preProcess(str1);
		   str2 = preProcess(str2);
		   if (str1.length() != str2.length()){
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
	   // are converted to lower-case, and all the other characters are deleted. For example, 
	   // the string "What? No way!" becomes "whatnoway"
	   public static String preProcess(String str) {
		   String ans = "";
		   for (int i = 0; i < str.length(); i++) {
			   char c = str.charAt(i);
			   if (c >= 'a' && c <= 'z') {
				   ans += c;
			   }
			   if (c >= 'A' && c <= 'Z') {
				   ans += (char)(c - ('A' - 'a'));
			   }
		   }
	       return ans;
	   } 
	   
	   // Returns a random anagram of the given string. The random anagram consists of the same
	   // letter characters as the given string, arranged in a random order. 
	   public static String randomAnagram(String str) {
		   String ans = "";
		   while (str.length() > 0) {
			   int rand = (int)(str.length() * Math.random());
			   ans += str.charAt(rand);
			   str = str.substring(0, rand) + str.substring(rand + 1);
		   }
	       return ans;
	   }
}
