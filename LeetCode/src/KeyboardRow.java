import java.util.ArrayList;
import java.util.List;

public class KeyboardRow {
	public String[] findWords(String[] words) {
		String[] keyboard = {"qwertyuiop", "asdfghjkl", "zxcvbnm"};
		List<String> list = new ArrayList<>();
		for (String originWord : words) {
			String word = originWord.toLowerCase();
			char[] chars = word.toCharArray();
			boolean flag = true;
			for (char item : chars) {
				if (keyboard[0].contains(word.charAt(0) + "") && !keyboard[0].contains(item + ""))
					flag = false;
				else if (keyboard[1].contains(word.charAt(0) + "") && !keyboard[1].contains(item + ""))
					flag = false;
				else if (keyboard[2].contains(word.charAt(0) + "") && !keyboard[2].contains(item + ""))
					flag = false;
			}
			if (flag)
				list.add(originWord);
		}

		return list.toArray(new String[list.size()]);
	}
}
