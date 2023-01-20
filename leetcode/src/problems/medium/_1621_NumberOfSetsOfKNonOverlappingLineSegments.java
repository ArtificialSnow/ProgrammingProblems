package problems.medium;

public class _1621_NumberOfSetsOfKNonOverlappingLineSegments {
    public int numberOfSets(int n, int k) {
        Integer[][][] dp = new Integer[n+1][k+1][2];
        return solve(n, k, true, dp);
    }

    private int solve(int n, int k, boolean previousRodEnded, Integer[][][] dp) {
        if (k == 0) return n;
        if (n < 2 || n-1 < k) return 0;
        int boolIndex = (previousRodEnded) ? 0 : 1;
        if (dp[n][k][boolIndex] != null) return dp[n][k][boolIndex];

        long sum = 0;
        if (previousRodEnded) {
            //don't start new rod
            sum += solve(n-1, k, true, dp);
            //start new rod
            sum += solve(n-1, k-1, false, dp);
        } else {
            //end rod and dont start new rod
            sum += solve(n-1, k, true, dp);
            //end rod and start new rod
            sum += solve(n-1, k-1, false, dp);
            //extend rod
            sum += solve(n-1, k, false, dp);
        }
        sum %= 1000000007;
        dp[n][k][boolIndex] = (int)sum;
        return (int)sum;
    }
}
