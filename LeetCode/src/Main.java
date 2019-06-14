import easy.FlippingAnImage;
import easy.SortArrayByParity;
import easy.SquaresofaSortedArray;
import easy.UniqueEmailAddresses;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		String[] str={"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"};
		UniqueEmailAddresses uniqueEmailAddresses=new UniqueEmailAddresses();
		uniqueEmailAddresses.numUniqueEmails(str);
		//Arrays.stream(flippingAnImage.sortedSquares(a)).forEach(m-> System.out.print(m+"\t"));
	}
}
