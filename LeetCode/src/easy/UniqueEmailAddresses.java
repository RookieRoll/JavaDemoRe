package easy;

import java.util.HashSet;
import java.util.Set;

public class UniqueEmailAddresses {
	public int numUniqueEmails(String[] emails) {
		Set<String> set=new HashSet<>();
		for(String email:emails){
			String emailName=email.split("@")[0];
			String domain=email.split("@")[1];
			if(emailName.contains(".")){
				emailName= emailName.replace(".","");
			}
			if(emailName.contains("+")){
				emailName=emailName.split("\\+")[0];
			}
			set.add(emailName+"@"+domain);
		}
		return set.size();
	}
}
