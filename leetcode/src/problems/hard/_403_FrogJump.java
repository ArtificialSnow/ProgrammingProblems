package problems.hard;

public class _403_FrogJump {
    public boolean canCross(int[] stones) {
        int n = stones.length;
        if (n < 2) { return true; }
        if (stones[1] != 1) { return false; }

        boolean[][] dp = new boolean[n][n];
        dp[0][1] = true;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int diff = stones[i] - stones[j];

                if (diff < n && dp[j][diff]) {
                    if (i == n-1) { return true; }

                    int landing = stones[i] + diff;
                    if (landing == stones[n-1] || landing == stones[n-1] - 1 || landing == stones[n-1] + 1) { return true; }

                    dp[i][diff-1] = true;
                    dp[i][diff] = true;
                    dp[i][diff+1] = true;
                }
            }
        }

        return false;
    }
}
