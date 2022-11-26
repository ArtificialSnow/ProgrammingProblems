package problems.hard;

public class _887_SuperEggDrop {
    public int superEggDrop(int k, int n) {
        if (n < 2) return 1;
        Integer[][] dp = new Integer[n][k];
        return dfs(dp, n, k);
    }

    public Integer dfs(Integer[][] dp, int n, int e) {
        if (n < 1) return 0;
        if (n == 1) return 1;
        if (e == 1) return n;
        if (dp[n-1][e-1] != null) return dp[n-1][e-1];

        int low = 1, high = n, mid;
        while (low < high) {
            mid = (low+high)/2;
            int a = dfs(dp, mid-1, e-1);
            int b = dfs(dp, n-mid, e);

            if (a < b) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        dp[n-1][e-1] = 1 + Math.max(dfs(dp, low-1, e-1), dfs(dp, n-low, e));
        return dp[n-1][e-1];
    }
}
