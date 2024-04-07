import java.util.Arrays;

/*
Search in Rotated Sorted Array II

There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).

Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].

Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is not in nums.

You must decrease the overall operation steps as much as possible.



Example 1:

Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true
Example 2:

Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false


Constraints:

1 <= nums.length <= 5000
-104 <= nums[i] <= 104
nums is guaranteed to be rotated at some pivot.
-104 <= target <= 104
 */
public class Search81 {
    public static void main(String[] args) {
        int[] nums = {1, 3};
        Search81 search81 = new Search81();
        System.out.println(search81.search(nums, 3));
    }

    public boolean search(int[] nums, int target) {
        int i = 0;
        if (nums.length < 2) {
            return nums[0] == target;
        }
        for (; i < nums.length - 1; i++) {
            if (nums[i] == target) {
                return true;
            }
            if (nums[i] > nums[i + 1]) {
                break;
            }
        }
        if (i == nums.length - 1) {
            return nums[nums.length - 1] == target;
        }
        if (target >= nums[i + 1] && target <= nums[nums.length - 1]) {
            return Arrays.binarySearch(nums, i + 1, nums.length, target) >= 0;
        }
        return false;
    }
}
