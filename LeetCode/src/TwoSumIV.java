import entity.TreeNode;

import java.util.Objects;

/**
 * Given the root of a Binary Search Tree and a target number k, return true if there exist two elements in the BST such that their sum is equal to the given target.
 * <p>
 * Example 1:
 * <p>
 * Input: root = [5,3,6,2,4,null,7], k = 9
 * Output: true
 * Example 2:
 */
public class TwoSumIV {
	public static void main(String[] args) {
		TwoSumIV sumIV = new TwoSumIV();
		System.out.println(sumIV.findTarget(sumIV.build(), 4));
	}

	public boolean findTarget(TreeNode root, int k) {
		return find(root, root, k);
	}

	public boolean find(TreeNode root, TreeNode node, int target) {
		if (Objects.isNull(node)) {
			return false;
		}
		if (hasRequired(root, target - node.val, node.val)) {
			return true;
		}
		return find(root, node.left, target) || find(root, node.right, target);
	}

	boolean hasRequired(TreeNode node, int target, int rootValue) {
		if (Objects.isNull(node)) {
			return false;
		}
		if (target == node.val && node.val != rootValue) {
			return true;
		}
		if (target < node.val) {
			return hasRequired(node.left, target, rootValue);
		} else {
			return hasRequired(node.right, target, rootValue);
		}
	}

	public TreeNode build() {
//		TreeNode node2 = new TreeNode(2);
//		TreeNode node4 = new TreeNode(4);
//		TreeNode node7 = new TreeNode(7);
//		TreeNode node3 = new TreeNode(3, node2, node4);
//		TreeNode node6 = new TreeNode(6, null, node7);
//		TreeNode node5 = new TreeNode(5, node3, node6);
//		return node5;

		TreeNode node1 = new TreeNode(1);
		TreeNode node3 = new TreeNode(3);
		TreeNode node2 = new TreeNode(2, node1, node3);
		return node2;
	}
}
