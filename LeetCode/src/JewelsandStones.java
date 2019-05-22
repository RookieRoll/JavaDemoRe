
import java.util.List;
import java.util.stream.Collectors;


public class JewelsandStones {

	public int numJewelsInStonesForFaster(String J, String S) {
		int[] tempResult = new int[58];
		for (char type : J.toCharArray()) {
			tempResult[type - 'A'] = 1;
		}
		int count = 0;
		for (char stone : S.toCharArray()) {
			count += tempResult[stone - 'A'];
		}
		return count;
	}

	public int numJewelsInStones(String J, String S) {
		List<Integer> type = J.chars().boxed().collect(Collectors.toList());
		return (int) S.chars().filter(m -> type.contains(m)).count();
	}
}
