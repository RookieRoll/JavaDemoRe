package easy;

public class SortArrayByParity {
	public int[] sortArrayByParity(int[] A) {
		int i = 0;
		int right = A.length - 1;
		while (i < A.length) {
			if (i == right) {
				break;
			}
			if (A[i] % 2 != 0) {
				int temp=A[i];
				A[i]=A[right];
				A[right]=temp;
				right--;
			}
			else {
				i++;
			}
		}
		return A;
	}
}
