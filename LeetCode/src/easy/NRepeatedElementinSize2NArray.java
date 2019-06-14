package easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NRepeatedElementinSize2NArray {
	public int repeatedNTimes(int[] A) {
		List<Integer> temp = new ArrayList<>();

		for (int i = 0; i < A.length; i++) {
			if (temp.contains(A[i])) {
				return A[i];
			}

			temp.add(A[i]);
		}

		return 0;
	}
}
