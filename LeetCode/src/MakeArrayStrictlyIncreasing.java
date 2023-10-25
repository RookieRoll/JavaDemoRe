import java.util.Arrays;

/**
 * Given two integer arrays arr1 and arr2, return the minimum number of operations (possibly zero) needed to make arr1 strictly increasing.
 * <p>
 * In one operation, you can choose two indices 0 <= i < arr1.length and 0 <= j < arr2.length and do the assignment arr1[i] = arr2[j].
 * <p>
 * If there is no way to make arr1 strictly increasing, return -1.
 * Input: arr1 = [1,5,3,6,7], arr2 = [1,3,2,4]
 * Output: 1
 * Explanation: Replace 5 with 2, then arr1 = [1, 2, 3, 6, 7].
 */
public class MakeArrayStrictlyIncreasing {
	public static void main(String[] args) {
		MakeArrayStrictlyIncreasing increasing = new MakeArrayStrictlyIncreasing();
		int[] array1 = new int[]{0, 11, 6, 1, 4, 3};
		int[] array2 = new int[]{5, 4, 11, 10, 1, 0};
		System.out.println(increasing.makeArrayIncreasing(array1, array2));
	}

	public int makeArrayIncreasing(int[] arr1, int[] arr2) {
		Arrays.sort(arr2);
		int j = 0;
		int count = 0;
		for (int i = 0; i < arr1.length - 1; i++) {
			if (needReplace(arr1, i)) {
				while (j < arr2.length) {
					if (arr2[j] <= arr1[i + 1] && arr2[j] > arr1[i - 1]) {
						arr1[i] = arr2[j];
						break;
					}
					j++;
					if (j >= arr2.length) {
						count = -1;
					}
				}
				if (count >= 0) {
					count++;
				} else {
					return count;
				}
			}
		}
		return count;
	}

	public boolean needReplace(int[] arr1, int i) {
		if (i == 0) {
			return arr1[i] > arr1[i + 1];
		}
		return arr1[i] > arr1[i + 1] || arr1[i] <= arr1[i - 1];
	}
}
