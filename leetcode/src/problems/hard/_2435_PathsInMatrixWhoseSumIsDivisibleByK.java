package problems.hard;

public class _2435_PathsInMatrixWhoseSumIsDivisibleByK {

    public static int mod = 1000000007;

    public int numberOfPaths(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;
        int[][][] dp = new int[n][m][k];
        dp[0][0][(grid[0][0]) % k] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < k; j++) {
                dp[i][0][(grid[i][0] + j) % k] += dp[i-1][0][j];
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < k; j++) {
                dp[0][i][(grid[0][i] + j) % k] += dp[0][i-1][j];
            }
        }

        for (int h = 1; h < n; h++) {
            for (int i = 1; i < m; i++) {
                for (int j = 0; j < k; j++) {
                    dp[h][i][(grid[h][i] + j) % k] += dp[h-1][i][j];
                    dp[h][i][(grid[h][i] + j) % k] %= mod;
                    dp[h][i][(grid[h][i] + j) % k] += dp[h][i-1][j];
                    dp[h][i][(grid[h][i] + j) % k] %= mod;
                }
            }
        }

        return dp[n-1][m-1][0];
    }
}
