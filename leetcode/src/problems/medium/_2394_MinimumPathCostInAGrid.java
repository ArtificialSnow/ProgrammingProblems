package problems.medium;

public class _2394_MinimumPathCostInAGrid {
    public int minPathCost(int[][] grid, int[][] moveCost) {
        int m = grid.length;
        int n = grid[0].length;

        int[] prev = new int[n];
        for (int i = 0; i < n; i++) {
            prev[i] = grid[0][i];
        }

        for (int i = 1; i < m; i++) {
            int[] curr = new int[n];
            for (int j = 0; j < n; j++) {
                curr[j] = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    curr[j] = Math.min(curr[j], grid[i][j] + prev[k] + moveCost[grid[i-1][k]][j]);
                }
            }
            prev = curr;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, prev[i]);
        }

        return min;
    }
}
