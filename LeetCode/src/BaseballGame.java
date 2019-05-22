import java.util.Stack;

public class BaseballGame {
	public int calPoints(String[] ops) {
		Stack<Integer> stack = new Stack();
		for (String word : ops) {
			if ("C".equals(word)) {
				stack.pop();
			} else if ("D".equals(word)) {
				stack.push(stack.peek() * 2);
			} else if ("+".equals(word)) {
				int temp1 = stack.pop();
				Integer num = stack.peek() + temp1;
				stack.push(temp1);
				stack.push(num);
			} else {
				stack.push(Integer.parseInt(word));
				stack.push(Integer.parseInt(word));
			}
		}
		int sum = 0;
		while (!stack.empty()) {
			sum += stack.pop();
		}
		return sum;
	}
}
