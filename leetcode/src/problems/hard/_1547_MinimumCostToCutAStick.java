package problems.hard;

import java.util.Arrays;

// TODO this is slow
public class _1547_MinimumCostToCutAStick {
    public int minCost(int n, int[] cuts) {
        Arrays.sort(cuts);

        int[] modifiedCuts = new int[cuts.length+2];
        modifiedCuts[0] = 0;
        for (int i = 0; i < cuts.length; i++) {
            modifiedCuts[i+1] = cuts[i];
        }
        modifiedCuts[cuts.length+1] = n;

        Long[][] dp = new Long[cuts.length+2][cuts.length+2];
        return (int)dfs(dp, modifiedCuts, 0, cuts.length+1);
    }

    public long dfs(Long[][] dp, int[] cuts, int a, int b) {
        if (b-a <= 1) return 0;
        if (dp[a][b] != null) return dp[a][b];

        dp[a][b] = (long)Integer.MAX_VALUE;
        for (int i = a+1; i < b; i++) {
            dp[a][b] = Math.min(dp[a][b], cuts[b] - cuts[a] + dfs(dp, cuts, a, i) + dfs(dp, cuts, i, b));
        }

        return dp[a][b];
    }
}
