package problems.hard;

public class _124_BinaryTreeMaximumPathSum {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int maxPathSum(TreeNode root) {
        int[] sum = new int[1];
        sum[0] = Integer.MIN_VALUE;
        dfs(root, sum);

        return sum[0];
    }

    public int dfs(TreeNode node, int[] sum) {
        if (node == null) return 0;

        int leftMax = dfs(node.left, sum);
        int rightMax = dfs(node.right, sum);
        sum[0] = Math.max(sum[0], Math.max(leftMax, 0) + node.val + Math.max(rightMax, 0));
        return node.val + Math.max(0, Math.max(rightMax, leftMax));
    }
}
