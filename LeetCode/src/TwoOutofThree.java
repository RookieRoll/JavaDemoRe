import java.util.*;

/**
 * Given three integer arrays nums1, nums2, and nums3, return a distinct array containing all the values that are present in at least two out of the three arrays. You may return the values in any order.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,1,3,2], nums2 = [2,3], nums3 = [3]
 * Output: [3,2]
 * Explanation: The values that are present in at least two arrays are:
 * - 3, in all three arrays.
 * - 2, in nums1 and nums2.
 * Example 2:
 * <p>
 * Input: nums1 = [3,1], nums2 = [2,3], nums3 = [1,2]
 * Output: [2,3,1]
 * Explanation: The values that are present in at least two arrays are:
 * - 2, in nums2 and nums3.
 * - 3, in nums1 and nums2.
 * - 1, in nums1 and nums3.
 * Example 3:
 * <p>
 * Input: nums1 = [1,2,2], nums2 = [4,3,3], nums3 = [5]
 * Output: []
 * Explanation: No value is present in at least two arrays.
 */
public class TwoOutofThree {
	public static void main(String[] args) {
		TwoOutofThree twoOutofThree = new TwoOutofThree();
		int[] num1 = new int[]{1, 2, 2};
		int[] num2 = new int[]{4, 3, 3};
		int[] num3 = new int[]{};
		System.out.println(twoOutofThree.twoOutOfThree(num1, num2, num3));
	}

	public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
		Set<Integer> result = new HashSet<>();
		List<Integer> num1Set = Arrays.stream(nums1).boxed().toList();
		List<Integer> num2Set = Arrays.stream(nums2).boxed().toList();
		List<Integer> num3Set = Arrays.stream(nums3).boxed().toList();

		List<Integer> firstResult = num3Set.stream().filter(n -> num2Set.contains(n) || num1Set.contains(n)).toList();
		List<Integer> secondResult = num2Set.stream().filter(num1Set::contains).toList();
		result.addAll(firstResult);
		result.addAll(secondResult);
		return new ArrayList<>(result);
	}
}
