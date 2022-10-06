import java.util.Stack;

/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * <p>
 * An input string is valid if:
 * <p>
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Every close bracket has a corresponding open bracket of the same type.
 */
public class ValidParentheses {
	public static void main(String[] args) {
		ValidParentheses validParentheses = new ValidParentheses();
		System.out.println(validParentheses.isValid("({)}[]"));
	}

	public boolean isValid(String s) {
		Stack<Character> characterStack = new Stack<>();
		for (char single : s.toCharArray()) {
			if (single == '(' || single == '{' || single == '[') {
				characterStack.add(single);
			} else {
				if (characterStack.isEmpty()) {
					return false;
				}
				Character pop = characterStack.pop();
				if (pop == '(' && single != ')') {
					return false;
				}
				if (pop == '{' && single != '}') {
					return false;
				}
				if (pop == '[' && single != ']') {
					return false;
				}
			}
		}
		return characterStack.isEmpty();
	}
}
