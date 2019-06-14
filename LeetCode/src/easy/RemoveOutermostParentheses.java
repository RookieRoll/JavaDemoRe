package easy;

public class RemoveOutermostParentheses {
	public String removeOuterParentheses(String S){
		int count=0;
		StringBuilder  stringBuilder=new StringBuilder();
		for(char cha:S.toCharArray()){
			if(cha=='('&&count++>0){
				stringBuilder.append(cha);
			}
			if(cha==')'&&--count>0)
			{
				stringBuilder.append(cha);
			}
		}
		return stringBuilder.toString();
	}
}
