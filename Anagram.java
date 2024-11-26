import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent", "listen")); // true
		System.out.println(isAnagram("William Shakespeare", "I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie", "Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle", "I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));

		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");

		// Performs a stress test of randomAnagram
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass)
				break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		// Sort both strings
		str1 = preProcess(str1);
		str2 = preProcess(str2);
		char[] s1Array = str1.toCharArray();
		char[] s2Array = str2.toCharArray();
		Arrays.sort(s1Array);
		Arrays.sort(s2Array);

		// Compare sorted strings
		return Arrays.equals(s1Array, s2Array);
	}

	// Returns a preprocessed version of the given string: all the letter characters
	// are converted
	// to lower-case, and all the other characters are deleted, except for spaces,
	// which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
		return str.replaceAll("\\s", "").replaceAll("[^A-Za-z0-9]", "").toLowerCase();
	}

	// Returns a random anagram of the given string. The random anagram consists of
	// the same
	// characters as the given string, re-arranged in a random order.
	public static String randomAnagram(String str) {
		str = preProcess(str);
		// Convert String to a list of Characters
		List<Character> characters = new ArrayList<>();
		for (char c : str.toCharArray()) {
			characters.add(c);
		}

		// Shuffle the list
		Collections.shuffle(characters);

		// Convert the list back to String
		StringBuilder shuffledString = new StringBuilder();
		for (char c : characters) {
			shuffledString.append(c);
		}

		return shuffledString.toString();
	}
}
