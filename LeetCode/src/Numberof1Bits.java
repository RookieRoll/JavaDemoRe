import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Stream;

public class Numberof1Bits {
	public int hammingWeight(int n) {
		int count = (int) Integer.toBinaryString(n).chars().filter(m -> m == '1').count();
		return count;
	}
}
