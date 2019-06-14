package easy;

//TODO 未完成
public class NQueen {

	private int N;
	private int[] array;

	public NQueen(int n) {
		N = n;
		array = new int[N];
		for (int i = 0; i < N; i++) {
			array[i] = -1;
		}
	}

	public void found(int col) {
		for (int i = 0; i < N; i++) {
			if (canPlace(i)) {
				if (col == N) {
					print();
				} else {
					array[col] = i;
					found(col + 1);
				}
			}
		}
	}

	private void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (array[i] == j) {
					System.out.print("Q");
				} else {
					System.out.print("*");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * array 棋盘
	 * k 当前列
	 */
	private boolean canPlace(int k) {
		for (int i = 0; i < N; i++) {
			if (Math.abs(array[i] - i) == Math.abs(k - i)) {
				return false;
			}
			if (array[i] == k) {
				return false;
			}
		}
		return true;
	}
}
