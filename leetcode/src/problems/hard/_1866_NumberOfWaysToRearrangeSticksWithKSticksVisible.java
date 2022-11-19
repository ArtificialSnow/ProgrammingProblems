package problems.hard;

public class _1866_NumberOfWaysToRearrangeSticksWithKSticksVisible {

    public static int mod = 1000000007;

    public int rearrangeSticks(int n, int k) {
        long[][] dp = new long[n+1][n+1];
        dp[1][1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i][1] = (dp[i-1][1] * (i-1)) % mod;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= i; j++) {
                dp[i][j] = (dp[i-1][j-1] + dp[i-1][j] * (i-1)) % mod;
            }
        }

        return (int)dp[n][k];
    }
}
