package problems.hard;

public class _1463_CherryPickupII {
    public int cherryPickup(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;


        Integer[][][] dp = new Integer[rows][cols][cols];
        dp[0][0][cols-1] = grid[0][0] + grid[0][cols-1];
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < cols; k++) {
                    int numPicked;
                    if (j == k) {
                        numPicked = grid[i][j];
                    } else {
                        numPicked = grid[i][j] + grid[i][k];
                    }

                    int max = -1;
                    for (int l = Math.max(0, j-1); l < Math.min(cols, j+2); l++) {
                        for (int m = Math.max(0, k-1); m < Math.min(cols, k+2); m++) {
                            Integer soln = dp[i-1][l][m];
                            if (soln == null) continue;

                            max = Math.max(max, soln);
                        }
                    }

                    if (max != -1) dp[i][j][k] = max + numPicked;
                }
            }
        }

        int maxSoln = 0;
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < cols; j++) {
                Integer soln = dp[rows-1][i][j];
                if (soln == null) continue;
                maxSoln = Math.max(maxSoln, soln);
            }
        }

        return maxSoln;
    }
}
