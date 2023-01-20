package problems.medium;

public class _1706_WhereWillTheBallFall {
    public int[] findBall(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m+1][n];
        for (int j = 0; j < n; j++) {
            dp[0][j] = j;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == -1) {
                    dp[i+1][j] = -1;
                    continue;
                }

                if(grid[i][dp[i][j]] == 1) {
                    //right
                    if (dp[i][j] == n-1 || grid[i][dp[i][j]+1] == -1) {
                        dp[i+1][j] = -1;
                    } else {
                        dp[i+1][j] = dp[i][j] + 1;
                    }
                } else {
                    //left
                    if (dp[i][j] == 0 || grid[i][dp[i][j]-1] == 1) {
                        dp[i+1][j] = -1;
                    } else {
                        dp[i+1][j] = dp[i][j] - 1;
                    }
                }
            }
        }

        return dp[m];
    }
}
