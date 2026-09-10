/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    int count = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return count;
    }

    // Returns {sum, number of nodes}
    public int[] dfs(TreeNode root) {

        // Base case
        if (root == null) {
            return new int[]{0, 0};
        }

        // Get information from left subtree
        int[] left = dfs(root.left);

        // Get information from right subtree
        int[] right = dfs(root.right);

        // Calculate current subtree sum
        int sum = left[0] + right[0] + root.val;

        // Calculate current subtree node count
        int nodes = left[1] + right[1] + 1;

        // Calculate average
        int average = sum / nodes;

        // Check condition
        if (root.val == average) {
            count++;
        }

        // Return information of current subtree
        return new int[]{sum, nodes};
    }
}