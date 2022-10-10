/**
 * Given an array of integers nums, calculate the pivot index of this array.
 * <p>
 * The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to the sum of all the numbers strictly to the index's right.
 * <p>
 * If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left. This also applies to the right edge of the array.
 * <p>
 * Return the leftmost pivot index. If no such index exists, return -1.
 */
public class FindPivotIndex {
	public static void main(String[] args) {
		FindPivotIndex findPivotIndex = new FindPivotIndex();
		System.out.println(findPivotIndex.pivotIndex(new int[]{
				-1, -1, 0, 1, 1, 0
		}));
	}

	public int pivotIndex(int[] nums) {
		int leftSum = 0, rightSum = 0;
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}
		if (sum - nums[0] == 0) {
			return 0;
		}
		for (int i = 1; i < nums.length; i++) {
			leftSum += nums[i - 1];
			if (i < nums.length) {
				rightSum = sum - leftSum - nums[i];
				if (leftSum == rightSum) {
					return i;
				}
			} else {
				return -1;
			}
		}
		if (sum - nums[nums.length - 1] == 0) {
			return nums.length - 1;
		}
		return -1;
	}
}
