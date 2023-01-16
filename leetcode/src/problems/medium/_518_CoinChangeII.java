package problems.medium;

import java.util.Arrays;

public class _518_CoinChangeII {
    public int change(int amount, int[] coins) {
        Arrays.sort(coins);
        int[] dp = new int[amount+1];
        dp[0] = 1;

        for (int coin : coins) {
            if (coin > amount) break;
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i-coin];
            }
        }

        return dp[amount];
    }
}
