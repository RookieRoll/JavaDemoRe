import java.util.Set;

/**
 * Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.
 * <p>
 * Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
 */
public class MaximumNumberofVowelsinaSubstringofGivenLength {
	public static void main(String[] args) {
		MaximumNumberofVowelsinaSubstringofGivenLength max = new MaximumNumberofVowelsinaSubstringofGivenLength();
		System.out.println(max.maxVowels("abciiidef", 3));
	}

	public int maxVowels(String s, int k) {
		int ans = 0;
		var vowels = Set.of('a', 'e', 'i', 'o', 'u');
		for (int i = 0, winCnt = 0; i < s.length(); ++i) {
			if (vowels.contains(s.charAt(i))) {
				++winCnt;
			}
			if (i >= k && vowels.contains(s.charAt(i - k))) {
				--winCnt;
			}
			ans = Math.max(winCnt, ans);
		}
		return ans;
	}

//	public int maxVowels(String s, int k) {
//		List<Character> vowel = List.of('a', 'e', 'i', 'o', 'u');
//		int maxCount = 0;
//		for (int i = 0; i <= s.length() - k; i++) {
//			int currentCount = 0;
//			for (int j = i; j < i + k; j++) {
//				if (vowel.contains(s.charAt(j))) {
//					currentCount++;
//				}
//			}
//			maxCount = Math.max(maxCount, currentCount);
//		}
//		return maxCount;
//	}
}
