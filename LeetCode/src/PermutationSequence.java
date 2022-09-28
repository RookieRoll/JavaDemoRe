/**
 * The set [1, 2, 3, ..., n] contains a total of n! unique permutations.
 * <p>
 * By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
 * <p>
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * Given n and k, return the kth permutation sequence.
 */
public class PermutationSequence {

	public static void main(String[] args) {
		PermutationSequence sequence = new PermutationSequence();
		System.out.println(sequence.getPermutation(4, 4));
	}

	public String getPermutation(int n, int k) {
		StringBuilder sb = new StringBuilder();
		boolean[] used = new boolean[n];
		k = k - 1;
		int factor = 1;
		for (int i = 1; i < n; i++) {
			factor *= i;
		}

		for (int i = 0; i < n; i++) {
			int index = k / factor;
			k = k % factor;
			for (int j = 0; j < n; j++) {
				if (used[j] == false) {
					if (index == 0) {
						used[j] = true;
						sb.append((char) ('0' + j + 1));
						break;
					} else {
						index--;
					}
				}
			}
			if (i < n - 1) {
				factor = factor / (n - 1 - i);
			}
		}

		return sb.toString();
	}

}
