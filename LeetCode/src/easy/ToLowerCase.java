package easy;

import javax.xml.stream.events.Characters;

public class ToLowerCase {
	public String toLowerCase(String str) {
		StringBuilder builder=new StringBuilder();
		for(char cha:str.toCharArray()){
			if(cha>='A'&&cha<='Z') {
				builder.append(Character.toLowerCase(cha));
			} else{
				builder.append(cha);
			}
		}
		return builder.toString();
	}
}
