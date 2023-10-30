import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 输入一个数组，输出全排列
 */
public class Permute {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Stack path = new Stack();
        premute(nums, 0, results, used, path);
        return results;
    }

    private void premute(int[] nums, int depth, List<List<Integer>> results, boolean[] used, Stack path) {
        if (path.size() == nums.length) {
            results.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            premute(nums, depth + 1, results, used, path);
            path.pop();
            used[i] = false;
        }
    }

}
