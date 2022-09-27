import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.
 * <p>
 * Return all the possible results. You may return the answer in any order.
 */
public class RemoveInvalidParentheses {
	public static void main(String[] args) {
		RemoveInvalidParentheses removeInvalidParentheses=new RemoveInvalidParentheses();
		System.out.println(removeInvalidParentheses.removeInvalidParentheses("()())()"));
	}

	public List<String> removeInvalidParentheses(String s) {
		Set<String> result = new HashSet<>();
		int[] count = getLeftRightCount(s);
		dfs(s, 0, count[0], count[1], result);
		return new ArrayList<>(result);
	}

	private void dfs(String s, int startIndex, int leftCount, int rightCount, Set<String> result) {
		if (isStringValidate(s)) {
			result.add(s);
			return;
		}
		for (int i = startIndex; i < s.length(); i++) {
			if (startIndex < i && s.charAt(i) == s.charAt(i - 1)) {
				continue;
			}
			if (leftCount > 0 && s.charAt(i) == '(') {
				dfs(s.substring(0, i) + s.substring(i + 1), i, leftCount - 1, rightCount, result);
			}
			if (rightCount > 0 && s.charAt(i) == ')') {
				dfs(s.substring(0, i) + s.substring(i + 1), i, leftCount, rightCount - 1, result);
			}
		}
	}

	private boolean isStringValidate(String s) {
		int[] leftRightCount = getLeftRightCount(s);
		return leftRightCount[0] == 0 && leftRightCount[1] == 0;
	}

	private int[] getLeftRightCount(String s) {
		char[] chars = s.toCharArray();
		int[] count = new int[2];
		for (char charInfo : chars) {
			if (charInfo == '(') {
				count[0]++;
			}
			if (charInfo == ')') {
				if (count[0] > 0) {
					count[0]--;
				} else {
					count[1]++;
				}
			}
		}
		return count;
	}

}
