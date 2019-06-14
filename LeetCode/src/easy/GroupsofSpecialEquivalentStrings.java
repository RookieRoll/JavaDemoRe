package easy;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GroupsofSpecialEquivalentStrings {
	public int numSpecialEquivGroups(String[] A) {
		Set<String> set = new HashSet<>();
		for (String item : A) {
			char[] single = new char[item.length() / 2 + 1];
			char[] doubl = new char[item.length() / 2 + 1];
			for (int i = 0; i < item.length(); i++) {
				if (i % 2 == 0)
					doubl[i / 2] = item.charAt(i);
				else
					single[i / 2] = item.charAt(i);
			}
			Arrays.sort(doubl);
			Arrays.sort(single);
			String result = new String(single) + new String(doubl);
			set.add(result);
		}
		return set
				.size();
	}
}
