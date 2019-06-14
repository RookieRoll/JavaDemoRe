package easy;

import java.util.*;

public class UniqueMorseCodeWords {
	public int uniqueMorseRepresentations(String[] words) {
		String[] dic = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.",
				"---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
		Set<String> set = new HashSet<>();
		for (String word : words) {
			StringBuilder str = new StringBuilder();
			for (char cha : word.toCharArray()) {
				str.append(dic[cha - 'a']);
			}
			set.add(str.toString());
		}
		return set.size();
	}
}
