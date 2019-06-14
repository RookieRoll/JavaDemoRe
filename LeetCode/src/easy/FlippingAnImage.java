package easy;

public class FlippingAnImage {
	public int[][] flipAndInvertImage(int[][] A) {
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[i].length / 2; j++) {
				int temp = A[i][j];
				A[i][j] = A[i][A.length - 1 - j];
				A[i][A.length - 1 - j] = temp;
			}
		}

		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[i].length; j++) {
				A[i][j]=1-A[i][j];
			}
		}
		return A;
	}
}
