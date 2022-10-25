import java.util.List;

/**
 * You are given a string s of even length. Split this string into two halves of equal lengths, and let a be the first half and b be the second half.
 * <p>
 * Two strings are alike if they have the same number of vowels ('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'). Notice that s contains uppercase and lowercase letters.
 * <p>
 * Return true if a and b are alike. Otherwise, return false.
 */
public class DetermineifStringHalvesAreAlike {
	public static void main(String[] args) {
		DetermineifStringHalvesAreAlike determineifStringHalvesAreAlike = new DetermineifStringHalvesAreAlike();
		System.out.println(determineifStringHalvesAreAlike.halvesAreAlike("book"));
		System.out.println(determineifStringHalvesAreAlike.halvesAreAlike("textbook"));

	}

	public boolean halvesAreAlike(String s) {
		List<Character> vowels = List.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
		int middle = s.length() / 2;
		int left = 0, right = 0;
		for (int i = 0; i < s.length(); i++) {
			if (vowels.contains(s.charAt(i))) {
				if (i < middle) {
					left++;
				} else {
					right++;
				}
			}
		}
		return left == right;
	}

}
