import java.util.List;
import java.util.Stack;

public class ReverseVowelsofaString {
	public static void main(String[] args) {
		ReverseVowelsofaString reverseVowelsofaString = new ReverseVowelsofaString();
		System.out.println(reverseVowelsofaString.reverseVowels1("leetcode"));
	}

	public String reverseVowels(String s) {
		List<Character> vowels = List.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char chas = s.charAt(i);
			if (vowels.contains(chas)) {
				stack.add(chas);
			}
		}
		StringBuilder sb = new StringBuilder();
		for (char chas : s.toCharArray()) {
			if (vowels.contains(chas)) {
				sb.append(stack.pop());
			} else {
				sb.append(chas);
			}
		}
		return sb.toString();
	}

	public String reverseVowels1(String s) {
		List<Character> vowels = List.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
		int left = 0, right = s.length() - 1;
		char[] chars = s.toCharArray();

		while (left <= right) {
			if (vowels.contains(chars[right]) && vowels.contains(chars[left])) {
				char tmp = chars[right];
				chars[right] = chars[left];
				chars[left] = tmp;
				right--;
				left++;
			} else if (vowels.contains(chars[left])) {
				right--;
			} else if (vowels.contains(chars[right])) {
				left++;
			} else if (!vowels.contains(chars[right]) && !vowels.contains(chars[left])) {
				left++;
				right--;
			}

		}
		return new String(chars);
	}

}
