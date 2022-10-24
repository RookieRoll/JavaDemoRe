/**
 * You are given a string s that consists of lower case English letters and brackets.
 * <p>
 * Reverse the strings in each pair of matching parentheses, starting from the innermost one.
 * <p>
 * Your result should not contain any brackets.
 */
public class ReverseSubstringsBetweenEachPairofParentheses {
	public static void main(String[] args) {
		ReverseSubstringsBetweenEachPairofParentheses parentheses = new ReverseSubstringsBetweenEachPairofParentheses();
		System.out.println(parentheses.reverseParentheses("abaeioucd"));
	}

	public String reverseParentheses(String s) {
		String result = s;
		String reverseResult = reverse(s);
		if (reverseResult != null) {
			result = reverseResult;
		}
		while (reverseResult != null) {
			reverseResult = reverse(reverseResult);
			if (reverseResult != null) {
				result = reverseResult;
			}
		}
		return result;
	}

	public String reverse(String s) {
		int leftIndex = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				leftIndex = i;
			}
			if (s.charAt(i) == ')') {
				String tempValue = s.substring(leftIndex + 1, i);
				StringBuffer reverse = new StringBuffer();
				reverse.append(s, 0, leftIndex);
				reverse.append(new StringBuffer(tempValue).reverse());
				reverse.append(s, i + 1, s.length());
				return reverse.toString();
			}
		}
		return null;
	}


}
