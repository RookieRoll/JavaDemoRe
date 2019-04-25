import java.lang.reflect.Array;
import java.util.*;

public class ReorderLogFiles {
	public String[] reorderLogFiles(String[] logs) {
		List<String> letter=new ArrayList<>();
		List<String> digit=new ArrayList<>();

		for(String log:logs){
			String[] word=log.split(" ");
			if(Character.isDigit(word[1].charAt(0))){
				digit.add(log);
			}else{
				letter.add(log);
			}
		}
		Collections.sort(letter, (s1, s2) -> {
			int index1 = s1.indexOf(" ");
			String id1 = s1.substring(0, index1);
			String letter1 = s1.substring(index1+1);

			int index2 = s2.indexOf(" ");
			String id2 = s2.substring(0, index2);
			String letter2 = s2.substring(index2+1);
			int v1 = letter1.compareTo(letter2);
			if(v1 != 0) return v1;
			int v2 = id1.compareTo(id2);
			return v2;
		});
		letter.addAll(digit);
		return letter.toArray(new String[letter.size()]);
	}

}
