import java.util.Arrays;

public class SumofEvenNumbersAfterQueries {

	public static void main(String[] args) {
		SumofEvenNumbersAfterQueries sumofEvenNumbersAfterQueries = new SumofEvenNumbersAfterQueries();
		int[] nums = new int[]{1};
		int[][] queries = new int[][]{
				{4, 0}
		};
		System.out.println(Arrays.toString(sumofEvenNumbersAfterQueries.sumEvenAfterQueries(nums, queries)));
	}

	public int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
		int[] result = new int[nums.length];
		int sum = 0;
		for (int num : nums) {
			if (num % 2 == 0) {
				sum += num;
			}
		}

		for (int i = 0; i < queries.length; i++) {
			int[] query = queries[i];
			int index = query[1], value = query[0], num = nums[index], current = num + value;
			nums[index] = current;
			if (num % 2 == 0) {
				sum -= num;
			}

			if (current % 2 == 0) {
				sum += current;
			}
			result[i] = sum;
		}
		return result;
	}
}
