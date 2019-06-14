import easy.FlippingAnImage;
import easy.SortArrayByParity;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		SortArrayByParity flippingAnImage=new SortArrayByParity();
		int a[]={3,1,2,4};
		Arrays.stream(flippingAnImage.sortArrayByParity(a)).forEach(m-> System.out.print(m));
	}
}
