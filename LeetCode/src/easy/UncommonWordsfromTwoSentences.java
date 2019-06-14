package easy;

import java.util.*;

public class UncommonWordsfromTwoSentences {
	public String[] uncommonFromSentences(String A, String B) {
		Map<String, Integer> map = new HashMap<>();
		String[] strA = (A + " " + B).split(" ");


		for (String word : strA) {
			if (map.containsKey(word)) {
				Integer value = map.get(word);
				value++;
				map.put(word, value);
			} else {
				map.put(word, 1);
			}
		}

		List<String> result = new ArrayList<>();
		for (String obj : map.keySet()) {
			if (map.get(obj) == 1)
				result.add(obj);
		}
		return result.toArray(new String[result.size()]);

	}
}
