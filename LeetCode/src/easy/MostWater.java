package easy;

public class MostWater {

	public int MaxArea(int[] height) {
		if (height == null || height.length < 2)
			return 0;
		int left = 0;
		int right = height.length - 1;
		int max = 0;
		while (left < right) {
			max = Math.max(max, Math.min(height[left], height[right]) * (right - left));
			if (height[right] > height[left])
				left++;
			else
				right--;
		}
		return max;

	}
}
