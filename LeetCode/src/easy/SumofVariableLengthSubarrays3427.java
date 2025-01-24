package easy;

/**
 * https://leetcode.com/problems/sum-of-variable-length-subarrays/description/
 * You are given an integer array nums of size n. For each index i where 0 <= i < n, define a
 * subarray
 * nums[start ... i] where start = max(0, i - nums[i]).
 * <p>
 * Return the total sum of all elements from the subarray defined for each index in the array.
 */
public class SumofVariableLengthSubarrays3427 {
    public static void main(String[] args) {
        int[] nums = {2, 3, 1};
        SumofVariableLengthSubarrays3427 subarrays = new SumofVariableLengthSubarrays3427();
        System.out.println(subarrays.subarraySum(nums));
    }

    public int subarraySum(int[] nums) {
        int totalSum = 0;
        for (int i = 0; i < nums.length; i++) {
            int start = Math.max(0, i - nums[i]);
            for (int j = start; j <= i; j++) {
                totalSum += nums[j];
            }
        }
        return totalSum;
    }
}
